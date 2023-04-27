package com.apress.spring6recipes.bank;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryAccountDao implements AccountDao {

	private final Map<String, Account> accounts = new ConcurrentHashMap<>();

	boolean accountExists(String accountNo) {
		return accounts.containsKey(accountNo);
	}

	@Override
	public void createAccount(Account account) {
		if (accountExists(account.getAccountNo())) {
			throw new DuplicateAccountException();
		}
		accounts.put(account.getAccountNo(), account);
	}

	@Override
	public void updateAccount(Account account) {
		if (!accountExists(account.getAccountNo())) {
			throw new AccountNotFoundException();
		}
		accounts.put(account.getAccountNo(), account);
	}

	@Override
	public void removeAccount(Account account) {
		if (!accountExists(account.getAccountNo())) {
			throw new AccountNotFoundException();
		}
		accounts.remove(account.getAccountNo());
	}

	@Override
	public Account findAccount(String accountNo) {
		var account = accounts.get(accountNo);
		if (account == null) {
			throw new AccountNotFoundException();
		}
		return account;
	}
}
