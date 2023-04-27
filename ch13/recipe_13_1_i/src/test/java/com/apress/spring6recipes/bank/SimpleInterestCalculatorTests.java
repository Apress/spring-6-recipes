package com.apress.spring6recipes.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SimpleInterestCalculatorTests {

	private InterestCalculator interestCalculator;

	@BeforeEach
	void init() {
		interestCalculator = new SimpleInterestCalculator();
		interestCalculator.setRate(0.05);
	}

	@Test
	void calculate() {
		var interest = interestCalculator.calculate(10000, 2);
		assertEquals(1000.0,interest, 0);
	}

	@Test
	void illegalCalculate() {
		assertThrows(IllegalArgumentException.class,
						() -> interestCalculator.calculate(-10000, 2));
	}

}
