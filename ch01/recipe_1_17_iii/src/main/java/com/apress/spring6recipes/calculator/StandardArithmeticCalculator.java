package com.apress.spring6recipes.calculator;

import org.springframework.stereotype.Component;

@Component
@LoggingRequired
class StandardArithmeticCalculator implements ArithmeticCalculator {

	@Override
	public double add(double a, double b) {
		var result = a + b;
		System.out.printf("%f + %f = %f%n", a, b, result);
		return result;
	}

	@Override
	public double sub(double a, double b) {
		var result = a - b;
		System.out.printf("%f - %f = %f%n", a, b, result);
		return result;
	}

	@Override
	public double mul(double a, double b) {
		var result = a * b;
		System.out.printf("%f * %f = %f%n", a, b, result);
		return result;
	}

	@Override
	public double div(double a, double b) {
		if (b == 0) {
			throw new IllegalArgumentException("Division by zero");
		}
		var result = a / b;
		System.out.printf("%f / %f = %f%n", a, b, result);
		return result;
	}
}
