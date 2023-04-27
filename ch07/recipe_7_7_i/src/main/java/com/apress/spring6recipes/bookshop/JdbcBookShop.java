package com.apress.spring6recipes.bookshop;

import com.apress.spring6recipes.utils.Utils;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

public class JdbcBookShop extends JdbcDaoSupport implements BookShop {

	public JdbcBookShop(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Transactional
	public void purchase(String isbn, String username) {
		var BOOK_SQL = "SELECT PRICE FROM BOOK WHERE ISBN = ?";
		int price = getJdbcTemplate().queryForObject(BOOK_SQL, Integer.class, isbn);

		var STOCK_SQL = "UPDATE BOOK_STOCK SET STOCK = STOCK - 1 WHERE ISBN = ?";
		getJdbcTemplate().update(STOCK_SQL, isbn);

		var BALANCE_SQL = "UPDATE ACCOUNT SET BALANCE = BALANCE - ? WHERE USERNAME = ?";
		getJdbcTemplate().update(BALANCE_SQL, price, username);

	}

	@Transactional
	public void increaseStock(String isbn, int stock) {
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + " - Prepare to increase book stock");

		var STOCK_SQL = "UPDATE BOOK_STOCK SET STOCK = STOCK + ? WHERE ISBN = ?";
		getJdbcTemplate().update(STOCK_SQL, stock, isbn);

		System.out.println(threadName + " - Book stock increased by " + stock);
		sleep(threadName);

		System.out.println(threadName + " - Book stock rolled back");
		throw new RuntimeException("Increased by mistake");
	}

	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public int checkStock(String isbn) {
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + " - Prepare to check book stock");

		var STOCK_SQL = "SELECT STOCK FROM BOOK_STOCK WHERE ISBN = ?";
		int stock = getJdbcTemplate().queryForObject(STOCK_SQL, Integer.class, isbn);

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
