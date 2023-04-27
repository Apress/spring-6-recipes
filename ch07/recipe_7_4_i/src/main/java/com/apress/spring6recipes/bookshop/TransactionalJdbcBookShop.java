package com.apress.spring6recipes.bookshop;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

public class TransactionalJdbcBookShop extends JdbcDaoSupport implements BookShop {

	private final PlatformTransactionManager transactionManager;

	public TransactionalJdbcBookShop(PlatformTransactionManager txManager, DataSource ds) {
		this.transactionManager = txManager;
		setDataSource(ds);
	}

	public void purchase(final String isbn, final String username) {
		var txTemplate = new TransactionTemplate(transactionManager);

		txTemplate.execute(new TransactionCallbackWithoutResult() {

			protected void doInTransactionWithoutResult(TransactionStatus ts) {

				var BOOK_SQL = "SELECT PRICE FROM BOOK WHERE ISBN = ?";
				int price = getJdbcTemplate().queryForObject(BOOK_SQL, Integer.class, isbn);

				var STOCK_SQL = "UPDATE BOOK_STOCK SET STOCK = STOCK - 1 WHERE ISBN = ?";
				getJdbcTemplate().update(STOCK_SQL, isbn);

				var BALANCE_SQL = "UPDATE ACCOUNT SET BALANCE = BALANCE - ? WHERE USERNAME = ?";
				getJdbcTemplate().update(BALANCE_SQL, price, username);
			}
		});
	}
}
