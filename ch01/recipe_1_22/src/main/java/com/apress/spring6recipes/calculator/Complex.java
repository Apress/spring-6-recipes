package com.apress.spring6recipes.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Configurable
@Component
@Scope("prototype")
public class Complex {

	private final int real;
	private final int imaginary;
	private ComplexFormatter formatter;

	public Complex(int real, int imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}

	public int imaginary() {
		return imaginary;
	}

	public int real() {
		return real;
	}

	@Autowired
	public void setFormatter(ComplexFormatter formatter) {
		this.formatter = formatter;
	}

	@Override
	public String toString() {
		return formatter.format(this);
	}
}
