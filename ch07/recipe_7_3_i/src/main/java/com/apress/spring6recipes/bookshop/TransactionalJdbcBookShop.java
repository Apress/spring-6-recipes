package com.apress.spring6recipes.bookshop;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;

public class TransactionalJdbcBookShop extends JdbcDaoSupport implements BookShop {

	private final PlatformTransactionManager transactionManager;

	public TransactionalJdbcBookShop(PlatformTransactionManager transactionManager, DataSource dataSource) {
		this.transactionManager = transactionManager;
		setDataSource(dataSource);
	}

	public void purchase(String isbn, String username) {
		var def = new DefaultTransactionDefinition();
		var status = transactionManager.getTransaction(def);

		try {
			var BOOK_SQL = "SELECT PRICE FROM BOOK WHERE ISBN = ?";
			var price = getJdbcTemplate().queryForObject(BOOK_SQL, Integer.class, isbn);

			var STOCK_SQL = "UPDATE BOOK_STOCK SET STOCK = STOCK - 1 WHERE ISBN = ?";
			getJdbcTemplate().update(STOCK_SQL, isbn);

			var BALANCE_SQL = "UPDATE ACCOUNT SET BALANCE = BALANCE - ? WHERE USERNAME = ?";
			getJdbcTemplate().update(BALANCE_SQL, price, username);

			transactionManager.commit(status);
		}
		catch (DataAccessException e) {
			transactionManager.rollback(status);
			throw e;
		}
	}
}
