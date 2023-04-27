package com.apress.spring6recipes.bank;

public interface InterestCalculator {

	void setRate(double rate);
	double calculate(double amount, double year);
}
