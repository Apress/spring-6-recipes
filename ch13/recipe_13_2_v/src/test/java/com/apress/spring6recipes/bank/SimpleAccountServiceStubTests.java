package com.apress.spring6recipes.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleAccountServiceStubTests {

	private static final String TEST_ACCOUNT_NO = "1234";

	private AccountDaoStub accountDaoStub;
	private AccountService accountService;

	@BeforeEach
	public void init() {
		accountDaoStub = new AccountDaoStub();
		accountDaoStub.accountNo = TEST_ACCOUNT_NO;
		accountDaoStub.balance = 100;
		accountService = new SimpleAccountService(accountDaoStub);
	}

	@Test
	void deposit() {
		accountService.deposit(TEST_ACCOUNT_NO, 50);
		assertEquals(TEST_ACCOUNT_NO, accountDaoStub.accountNo);
		assertEquals(150, accountDaoStub.balance, 0);
	}

	@Test
	void withdrawWithSufficientBalance() {
		accountService.withdraw(TEST_ACCOUNT_NO, 50);
		assertEquals(TEST_ACCOUNT_NO, accountDaoStub.accountNo);
		assertEquals(50, accountDaoStub.balance, 0);
	}

	@Test
	void withdrawWithInsufficientBalance() {
		assertThrows(InsufficientBalanceException.class, () -> accountService.withdraw(TEST_ACCOUNT_NO, 150));
	}

	/**
	 * Partially implemented stub implementation for the {@code AccountDao}
	 */
	private static class AccountDaoStub implements AccountDao {

		private String accountNo;
		private double balance;

		public void createAccount(Account account) {}

		public void removeAccount(Account account) {}

		public Account findAccount(String accountNo) {
			return new Account(this.accountNo, this.balance);
		}

		public void updateAccount(Account account) {
			this.accountNo = account.getAccountNo();
			this.balance = account.getBalance();
		}
	}
}