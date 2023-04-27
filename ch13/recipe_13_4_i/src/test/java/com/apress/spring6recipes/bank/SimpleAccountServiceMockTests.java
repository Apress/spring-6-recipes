package com.apress.spring6recipes.bank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class SimpleAccountServiceMockTests {

	private static final String TEST_ACCOUNT_NO = "1234";

	@Mock
	private AccountDao accountDao;

	@InjectMocks
	private SimpleAccountService accountService;

	@Test
	public void deposit() {
		// Setup
		Account account = new Account(TEST_ACCOUNT_NO, 100);
		when(accountDao.findAccount(TEST_ACCOUNT_NO)).thenReturn(account);

		// Execute
		accountService.deposit(TEST_ACCOUNT_NO, 50);

		// Verify
		verify(accountDao, times(1)).findAccount(any(String.class));
		verify(accountDao, times(1)).updateAccount(account);

	}

	@Test
	public void withdrawWithSufficientBalance() {
		// Setup
		Account account = new Account(TEST_ACCOUNT_NO, 100);
		when(accountDao.findAccount(TEST_ACCOUNT_NO)).thenReturn(account);

		// Execute
		accountService.withdraw(TEST_ACCOUNT_NO, 50);

		// Verify
		verify(accountDao, times(1)).findAccount(any(String.class));
		verify(accountDao, times(1)).updateAccount(account);

	}

	@Test
	public void testWithdrawWithInsufficientBalance() {
		// Setup
		Account account = new Account(TEST_ACCOUNT_NO, 100);
		when(accountDao.findAccount(TEST_ACCOUNT_NO)).thenReturn(account);

		// Execute
		assertThrows(InsufficientBalanceException.class, () -> accountService.withdraw(TEST_ACCOUNT_NO, 150));
	}

}
