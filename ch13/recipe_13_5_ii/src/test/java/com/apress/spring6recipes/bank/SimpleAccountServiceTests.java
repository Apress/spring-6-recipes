package com.apress.spring6recipes.bank;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SimpleAccountServiceTests {

	private static final String TEST_ACCOUNT_NO = "1234";

	private SimpleAccountService accountService;

	@BeforeMethod
	public void init() {
		accountService = new SimpleAccountService(new InMemoryAccountDao());
		accountService.createAccount(TEST_ACCOUNT_NO);
		accountService.deposit(TEST_ACCOUNT_NO, 100);
	}

	@Test
	public void deposit() {
		accountService.deposit(TEST_ACCOUNT_NO, 50);
		assertEquals(accountService.getBalance(TEST_ACCOUNT_NO), 150, 0);
	}

	@Test
	public void withDraw() {
		accountService.withdraw(TEST_ACCOUNT_NO, 50);
		assertEquals(accountService.getBalance(TEST_ACCOUNT_NO), 50, 0);
	}

	@AfterMethod
	public void cleanup() {
		accountService.removeAccount(TEST_ACCOUNT_NO);
	}

}
