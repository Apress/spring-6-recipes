package com.apress.spring6recipes.bank;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class SimpleAccountService implements AccountService {

	private final AccountDao accountDao;

	public SimpleAccountService(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public void createAccount(String accountNo) {
		accountDao.createAccount(new Account(accountNo, 0));
	}

	@Override
	public void removeAccount(String accountNo) {
		var account = accountDao.findAccount(accountNo);
		accountDao.removeAccount(account);
	}

	@Override
	public void deposit(String accountNo, double amount) {
		var account = accountDao.findAccount(accountNo);
		account.setBalance(account.getBalance() + amount);
		accountDao.updateAccount(account);
	}

	@Override
	public void withdraw(String accountNo, double amount) {
		var account = accountDao.findAccount(accountNo);
		if (account.getBalance() < amount) {
			throw new InsufficientBalanceException();
		}
		account.setBalance(account.getBalance() - amount);
		accountDao.updateAccount(account);
	}

	@Override
	public double getBalance(String accountNo) {
		return accountDao.findAccount(accountNo).getBalance();
	}
}
