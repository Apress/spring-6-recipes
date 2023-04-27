package com.apress.spring6recipes.caching;

import java.math.BigDecimal;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.spring6recipes.caching.config.CalculationConfiguration;

public class Main {

	public static final void main(String[] args) {
		var cfg = CalculationConfiguration.class;
		try (var context = new AnnotationConfigApplicationContext(cfg)) {
			var calculationService = context.getBean(CalculationService.class);
			for (int i = 0; i < 5; i++) {
				var start = System.currentTimeMillis();
				var result = calculationService.heavyCalculation(BigDecimal.valueOf(2L), 16);
				var duration = System.currentTimeMillis() - start;
				System.out.printf("Result: %.0f, Took: %dms%n", result, duration);
			}
		}
	}
}
