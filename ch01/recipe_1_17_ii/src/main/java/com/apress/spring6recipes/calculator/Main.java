package com.apress.spring6recipes.calculator;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {

		try (var context = new AnnotationConfigApplicationContext(CalculatorConfiguration.class)) {

			var arithmeticCalculator = context.getBean("arithmeticCalculator", ArithmeticCalculator.class);
			arithmeticCalculator.add(1, 2);
			arithmeticCalculator.sub(4, 3);
			arithmeticCalculator.mul(2, 3);
			arithmeticCalculator.div(4, 2);

			var unitCalculator = context.getBean("unitCalculator", UnitCalculator.class);
			unitCalculator.kilogramToPound(10);
			unitCalculator.kilometerToMile(5);
		}
	}

}
