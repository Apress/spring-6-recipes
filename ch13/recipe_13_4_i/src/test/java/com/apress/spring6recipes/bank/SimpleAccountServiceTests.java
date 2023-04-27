package com.apress.spring6recipes.bank;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleAccountServiceTests {

	private static final String TEST_ACCOUNT_NO = "1234";

	private AccountService accountService;

	@BeforeEach
	public void init() {
		accountService = new SimpleAccountService(new InMemoryAccountDao());
		accountService.createAccount(TEST_ACCOUNT_NO);
		accountService.deposit(TEST_ACCOUNT_NO, 100);
	}

	@Test
	public void deposit() {
		accountService.deposit(TEST_ACCOUNT_NO, 50);
		assertEquals(150, accountService.getBalance(TEST_ACCOUNT_NO), 0);
	}

	@Test
	public void withDraw() {
		accountService.withdraw(TEST_ACCOUNT_NO, 50);
		assertEquals(50, accountService.getBalance(TEST_ACCOUNT_NO), 0);
	}

	@AfterEach
	public void cleanup() {
		accountService.removeAccount(TEST_ACCOUNT_NO);
	}

}
