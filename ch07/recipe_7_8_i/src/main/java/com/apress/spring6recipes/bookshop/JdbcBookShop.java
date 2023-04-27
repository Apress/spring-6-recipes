package com.apress.spring6recipes.bookshop;

import java.io.IOException;

import com.apress.spring6recipes.utils.Utils;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

public class JdbcBookShop extends JdbcDaoSupport implements BookShop {

	public JdbcBookShop(DataSource dataSource) {
		setDataSource(dataSource);
	}
	@Transactional(
						propagation = Propagation.REQUIRES_NEW,
						rollbackFor = IOException.class,
						noRollbackFor = ArithmeticException.class)
	public void purchase(String isbn, String username) {
		int price = getJdbcTemplate().queryForObject("SELECT PRICE FROM BOOK WHERE ISBN = ?", Integer.class, isbn);

		getJdbcTemplate().update("UPDATE BOOK_STOCK SET STOCK = STOCK - 1 WHERE ISBN = ?", isbn);

		getJdbcTemplate().update("UPDATE ACCOUNT SET BALANCE = BALANCE - ? WHERE USERNAME = ?", price, username);
	}

	@Transactional
	public void increaseStock(String isbn, int stock) {
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + " - Prepare to increase book stock");

		getJdbcTemplate().update("UPDATE BOOK_STOCK SET STOCK = STOCK + ? WHERE ISBN = ?", stock, isbn);

		System.out.println(threadName + " - Book stock increased by " + stock);
		sleep(threadName);

		System.out.println(threadName + " - Book stock rolled back");
		throw new RuntimeException("Increased by mistake");
	}

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public int checkStock(String isbn) {
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + " - Prepare to check book stock");

		int stock = getJdbcTemplate().queryForObject("SELECT STOCK FROM BOOK_STOCK WHERE ISBN = ?", Integer.class,
				isbn);

		System.out.println(threadName + " - Book stock is " + stock);
		sleep(threadName);

		return stock;
	}

	private void sleep(String threadName) {
		System.out.println(threadName + " - Sleeping");
		Utils.sleep(10000);
		System.out.println(threadName + " - Wake up");
	}

}
