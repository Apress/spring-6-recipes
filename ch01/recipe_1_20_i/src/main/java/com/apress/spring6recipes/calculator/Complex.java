package com.apress.spring6recipes.calculator;

public record Complex(int real, int imaginary) {

	@Override
	public String toString() {
		return "(" + real + " + " + imaginary + "i)";
	}
}