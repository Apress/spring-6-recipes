package com.apress.spring6recipes.bank;

public class SimpleInterestCalculator implements InterestCalculator {

	private double rate;

	@Override
	public void setRate(double rate) {
		this.rate = rate;
	}

	@Override
	public double calculate(double amount, double year) {
		if (amount < 0 || year < 0) {
			throw new IllegalArgumentException("Amount or year must be positive");
		}
		return amount * year * rate;
	}
}
