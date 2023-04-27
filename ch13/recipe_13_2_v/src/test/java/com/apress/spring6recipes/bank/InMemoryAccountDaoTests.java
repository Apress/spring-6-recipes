package com.apress.spring6recipes.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryAccountDaoTests {

	private static final String EXISTING_ACCOUNT_NO = "1234";
	private static final String NEW_ACCOUNT_NO = "5678";

	private Account existingAccount;
	private Account newAccount;
	private InMemoryAccountDao accountDao;

	@BeforeEach
	void init() {
		existingAccount = new Account(EXISTING_ACCOUNT_NO, 100);
		newAccount = new Account(NEW_ACCOUNT_NO, 200);
		accountDao = new InMemoryAccountDao();
		accountDao.createAccount(existingAccount);
	}

	@Test
	void accountExists() {
		assertTrue(accountDao.accountExists(EXISTING_ACCOUNT_NO));
		assertFalse(accountDao.accountExists(NEW_ACCOUNT_NO));
	}

	@Test
	void createNewAccount() {
		accountDao.createAccount(newAccount);
		assertEquals(newAccount, accountDao.findAccount(NEW_ACCOUNT_NO));
	}

	@Test
	void createDuplicateAccount() {
		assertThrows(DuplicateAccountException.class, () ->
						accountDao.createAccount(existingAccount));
	}

	@Test
	void updateExistedAccount() {
		existingAccount.setBalance(150);
		accountDao.updateAccount(existingAccount);
		assertEquals(existingAccount, accountDao.findAccount(EXISTING_ACCOUNT_NO));
	}

	@Test
	void updateNotExistedAccount() {
		assertThrows(AccountNotFoundException.class, () ->
						accountDao.updateAccount(newAccount));
	}

	@Test
	void removeExistedAccount() {
		accountDao.removeAccount(existingAccount);
		assertFalse(accountDao.accountExists(EXISTING_ACCOUNT_NO));
	}

	@Test
	void removeNotExistedAccount() {
		assertThrows(AccountNotFoundException.class, () ->
						accountDao.removeAccount(newAccount));
	}

	@Test
	void findExistedAccount() {
		var account = accountDao.findAccount(EXISTING_ACCOUNT_NO);
		assertEquals(existingAccount, account);
	}

	@Test
	void findNotExistedAccount() {
		assertThrows(AccountNotFoundException.class, () -> accountDao.findAccount(NEW_ACCOUNT_NO));
	}

}
