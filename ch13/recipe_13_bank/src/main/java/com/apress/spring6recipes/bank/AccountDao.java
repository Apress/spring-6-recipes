package com.apress.spring6recipes.bank;

public interface AccountDao {

	void createAccount(Account account);
	void updateAccount(Account account);
	void removeAccount(Account account);
	Account findAccount(String accountNo);
}