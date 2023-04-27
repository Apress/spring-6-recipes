package com.apress.spring6recipes.calculator;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {

		var cfg = CalculatorConfiguration.class;
		try (var ctx = new AnnotationConfigApplicationContext(cfg)) {
			var complexCalculator = ctx.getBean( ComplexCalculator.class);

			complexCalculator.add(new Complex(1, 2), new Complex(2, 3));
			complexCalculator.sub(new Complex(5, 8), new Complex(2, 3));
		}
	}
}
