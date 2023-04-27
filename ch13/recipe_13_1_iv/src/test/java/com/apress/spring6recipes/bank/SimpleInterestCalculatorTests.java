package com.apress.spring6recipes.bank;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SimpleInterestCalculatorTests {

	private InterestCalculator interestCalculator;

	@BeforeMethod
	public void init() {
		interestCalculator = new SimpleInterestCalculator();
		interestCalculator.setRate(0.05);
	}

	@DataProvider(name = "legal")
	public Object[][] createLegalInterestParameters() {
		return new Object[][] {
						new Object[] { 10000, 2, 1000.0 },
						new Object[] {10000.0, 1, 500.0}};
	}

	@DataProvider(name = "illegal")
	public Object[][] createIllegalInterestParameters() {
		return new Object[][] {
						new Object[] { -10000, 2 },
						new Object[] { 10000, -2 },
						new Object[] { -10000, -2 } };
	}

	@Test(dataProvider = "legal")
	public void calculate(double amount, double year, double result) {
		double interest = interestCalculator.calculate(amount, year);
		assertEquals(interest, result);
	}

	@Test(dataProvider = "illegal", expectedExceptions = IllegalArgumentException.class)
	public void illegalCalculate(double amount, double year) {
		interestCalculator.calculate(amount, year);
	}
}