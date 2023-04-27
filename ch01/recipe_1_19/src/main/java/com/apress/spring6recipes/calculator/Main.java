package com.apress.spring6recipes.calculator;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		var cfg = CalculatorConfiguration.class;
		try (var context = new AnnotationConfigApplicationContext(cfg)) {
			var arithmeticCalculator = context.getBean(ArithmeticCalculator.class);
			arithmeticCalculator.add(1, 2);
			arithmeticCalculator.sub(4, 3);
			arithmeticCalculator.mul(2, 3);
			arithmeticCalculator.div(4, 2);

			var unitCalculator = context.getBean(UnitCalculator.class);
			unitCalculator.kilogramToPound(10);
			unitCalculator.kilometerToMile(5);

			var maxCalculator = (MaxCalculator) arithmeticCalculator;
			maxCalculator.max(1,2);

			var minCalculator = (MinCalculator)arithmeticCalculator;
			minCalculator.min( 1, 2);

			var arithmeticCounter = (Counter) arithmeticCalculator;
			System.out.println(arithmeticCounter.getCount());

			var unitCounter = (Counter) unitCalculator;
			System.out.println(unitCounter.getCount());
		}
	}
}
