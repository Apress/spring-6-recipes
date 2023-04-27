package com.apress.spring6recipes.caching;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.math.BigDecimal;
import java.time.Duration;

public class Main {

	public static final void main(String[] args) throws Exception {
		Cache<String, BigDecimal> cache = Caffeine.newBuilder()
						.maximumSize(1000)
						.expireAfterWrite(Duration.ofMinutes(5)).build();
		var calculationService = new PlainCachingCalculationService(cache);
		for (int i = 0; i < 5; i++) {
			var start = System.currentTimeMillis();
			var result = calculationService.heavyCalculation(BigDecimal.valueOf(2L), 16);
			var duration = System.currentTimeMillis() - start;
			System.out.printf("Result: %.0f, Took: %dms%n", result, duration);
		}
	}
}
