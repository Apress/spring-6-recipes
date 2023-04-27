package com.apress.spring6recipes.bank;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SimpleInterestCalculatorTests {

	private InterestCalculator interestCalculator;

	@BeforeMethod
	public void init() {
		interestCalculator = new SimpleInterestCalculator();
		interestCalculator.setRate(0.05);
	}

	@Test
	public void calculate() {
		var interest = interestCalculator.calculate(10000, 2);
		assertEquals(interest, 1000.0);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void illegalCalculate() {
		interestCalculator.calculate(-10000, 2);
	}

}
