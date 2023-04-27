package com.apress.spring6recipes.calculator;

import org.springframework.stereotype.Component;

@Component
public class ComplexFormatter {

	private String pattern = "(a + bi)";

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String format(Complex complex) {
		return pattern
						.replaceAll("a", Integer.toString(complex.real()))
						.replaceAll("b", Integer.toString(complex.imaginary()));
	}
}
