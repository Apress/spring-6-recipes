package com.apress.spring6recipes.calculator;

public class SimpleMaxCalculator implements MaxCalculator {

	public double max(double a, double b) {
		var result = Math.max(a, b);
		System.out.printf("max(%f,%f) = %f", a,b, result);
		return result;
	}

}
