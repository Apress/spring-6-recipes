package com.apress.spring6recipes.calculator;

public record Complex(int real, int imaginary) {

	public String toString() {
		return "(" + real + " + " + imaginary + "i)";
	}

}