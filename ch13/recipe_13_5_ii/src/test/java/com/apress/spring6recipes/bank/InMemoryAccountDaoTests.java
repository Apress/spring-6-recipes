package com.apress.spring6recipes.bank;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class InMemoryAccountDaoTests {

	private static final String EXISTING_ACCOUNT_NO = "1234";

	private static final String NEW_ACCOUNT_NO = "5678";

	private Account existingAccount;

	private Account newAccount;

	private InMemoryAccountDao accountDao;

	@BeforeMethod
	public void init() {
		existingAccount = new Account(EXISTING_ACCOUNT_NO, 100);
		newAccount = new Account(NEW_ACCOUNT_NO, 200);
		accountDao = new InMemoryAccountDao();
		accountDao.createAccount(existingAccount);
	}

	@Test
	public void accountExists() {
		assertTrue(accountDao.accountExists(EXISTING_ACCOUNT_NO));
		assertFalse(accountDao.accountExists(NEW_ACCOUNT_NO));
	}

	@Test
	public void createNewAccount() {
		accountDao.createAccount(newAccount);
		assertEquals(accountDao.findAccount(NEW_ACCOUNT_NO), newAccount);
	}

	@Test(expectedExceptions = DuplicateAccountException.class)
	public void createDuplicateAccount() {
		accountDao.createAccount(existingAccount);
	}

	@Test
	public void updateExistedAccount() {
		existingAccount.setBalance(150);
		accountDao.updateAccount(existingAccount);
		assertEquals(accountDao.findAccount(EXISTING_ACCOUNT_NO), existingAccount);
	}

	@Test(expectedExceptions = AccountNotFoundException.class)
	public void updateNotExistedAccount() {
		accountDao.updateAccount(newAccount);
	}

	@Test
	public void removeExistedAccount() {
		accountDao.removeAccount(existingAccount);
		assertFalse(accountDao.accountExists(EXISTING_ACCOUNT_NO));
	}

	@Test(expectedExceptions = AccountNotFoundException.class)
	public void removeNotExistedAccount() {
		accountDao.removeAccount(newAccount);
	}

	@Test
	public void findExistedAccount() {
		Account account = accountDao.findAccount(EXISTING_ACCOUNT_NO);
		assertEquals(account, existingAccount);
	}

	@Test(expectedExceptions = AccountNotFoundException.class)
	public void findNotExistedAccount() {
		accountDao.findAccount(NEW_ACCOUNT_NO);
	}

}
