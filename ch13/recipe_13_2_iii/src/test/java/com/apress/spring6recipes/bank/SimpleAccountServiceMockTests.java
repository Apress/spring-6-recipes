package com.apress.spring6recipes.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SimpleAccountServiceMockTests {

	private static final String TEST_ACCOUNT_NO = "1234";

	private AccountDao accountDao;
	private SimpleAccountService accountService;

	@BeforeEach
	public void init() {
		accountDao = mock(AccountDao.class);
		accountService = new SimpleAccountService(accountDao);
	}

	@Test
	void deposit() {
		var account = new Account(TEST_ACCOUNT_NO, 100);
		when(accountDao.findAccount(TEST_ACCOUNT_NO)).thenReturn(account);

		accountService.deposit(TEST_ACCOUNT_NO, 50);

		verify(accountDao, times(1)).findAccount(any(String.class));
		verify(accountDao, times(1)).updateAccount(account);
	}

	@Test
	void withdrawWithSufficientBalance() {
		var account = new Account(TEST_ACCOUNT_NO, 100);
		when(accountDao.findAccount(TEST_ACCOUNT_NO)).thenReturn(account);

		accountService.withdraw(TEST_ACCOUNT_NO, 50);

		verify(accountDao, times(1)).findAccount(any(String.class));
		verify(accountDao, times(1)).updateAccount(account);
	}

	@Test
	public void testWithdrawWithInsufficientBalance() {
		var account = new Account(TEST_ACCOUNT_NO, 100);
		when(accountDao.findAccount(TEST_ACCOUNT_NO)).thenReturn(account);

		assertThrows(InsufficientBalanceException.class, () ->
										accountService.withdraw(TEST_ACCOUNT_NO, 150));
	}
}
