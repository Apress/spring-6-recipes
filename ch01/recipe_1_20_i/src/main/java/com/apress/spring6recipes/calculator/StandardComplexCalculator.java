package com.apress.spring6recipes.calculator;

import org.springframework.stereotype.Component;

@Component
class StandardComplexCalculator implements ComplexCalculator {

	@Override
	public Complex add(Complex a, Complex b) {
		var result = new Complex(a.real() + b.real(), a.imaginary() + b.imaginary());
		System.out.printf("%s + %s = %s%n", a, b, result);
		return result;
	}

	@Override
	public Complex sub(Complex a, Complex b) {
		var result = new Complex(a.real() - b.real(), a.imaginary() - b.imaginary());
		System.out.printf("%s - %s = %s%n", a, b, result);
		return result;
	}
}
