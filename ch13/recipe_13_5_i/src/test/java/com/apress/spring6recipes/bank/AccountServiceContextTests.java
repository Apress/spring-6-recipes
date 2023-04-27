package com.apress.spring6recipes.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BankConfiguration.class)
@Transactional
@Sql(scripts = { "classpath:/bank.sql" })
class AccountServiceContextTests {

	private static final String TEST_ACCOUNT_NO = "1234";

	@Autowired
	private AccountService accountService;

	@BeforeEach
	void init() {
		accountService.createAccount(TEST_ACCOUNT_NO);
		accountService.deposit(TEST_ACCOUNT_NO, 100);
	}

	@Test
	void deposit() {
		accountService.deposit(TEST_ACCOUNT_NO, 50);
		assertEquals(150, accountService.getBalance(TEST_ACCOUNT_NO), 0);
	}

	@Test
	void withDraw() {
		accountService.withdraw(TEST_ACCOUNT_NO, 50);
		assertEquals(50, accountService.getBalance(TEST_ACCOUNT_NO), 0);
	}
}
