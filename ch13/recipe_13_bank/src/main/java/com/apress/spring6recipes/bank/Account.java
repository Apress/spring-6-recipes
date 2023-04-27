package com.apress.spring6recipes.bank;

import java.util.Objects;

public class Account {

	private final String accountNo;
	private double balance;

	public Account(String accountNo, double balance) {
		this.accountNo = accountNo;
		this.balance = balance;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o instanceof Account account)
			return Objects.equals(accountNo, account.accountNo);
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNo);
	}

	@Override
	public String toString() {
		return String.format("Account [accountNo='%s', balance=%d]", accountNo, balance);
	}
}
