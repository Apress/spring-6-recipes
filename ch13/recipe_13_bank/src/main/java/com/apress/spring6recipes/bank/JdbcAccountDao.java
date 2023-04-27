package com.apress.spring6recipes.bank;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

class JdbcAccountDao implements AccountDao {

	private final JdbcTemplate jdbcTemplate;

	JdbcAccountDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}

	@Override
	public void createAccount(Account account) {
		var sql = "INSERT INTO ACCOUNT (ACCOUNT_NO, BALANCE) VALUES (?, ?)";
		this.jdbcTemplate.update(sql, account.getAccountNo(), account.getBalance());
	}

	@Override
	public void updateAccount(Account account) {
		var sql = "UPDATE ACCOUNT SET BALANCE = ? WHERE ACCOUNT_NO = ?";
		this.jdbcTemplate.update(sql, account.getBalance(), account.getAccountNo());
	}

	@Override
	public void removeAccount(Account account) {
		var sql = "DELETE FROM ACCOUNT WHERE ACCOUNT_NO = ?";
		this.jdbcTemplate.update(sql, account.getAccountNo());
	}

	@Override
	public Account findAccount(String accountNo) {
		var sql = "SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_NO = ?";
		var balance = this.jdbcTemplate.queryForObject(sql, Double.class, accountNo);
		return new Account(accountNo, balance);
	}
}
