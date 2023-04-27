package com.apress.spring6recipes.bookshop;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

public class JdbcBookShop extends JdbcDaoSupport implements BookShop {

	public JdbcBookShop(DataSource dataSource) {
		setDataSource(dataSource);
	}
	@Transactional
	public void purchase(final String isbn, final String username) {

		int price = getJdbcTemplate().queryForObject("SELECT PRICE FROM BOOK WHERE ISBN = ?", Integer.class, isbn);

		getJdbcTemplate().update("UPDATE BOOK_STOCK SET STOCK = STOCK - 1 WHERE ISBN = ?", isbn);

		getJdbcTemplate().update("UPDATE ACCOUNT SET BALANCE = BALANCE - ? WHERE USERNAME = ?", price, username);
	}

}
