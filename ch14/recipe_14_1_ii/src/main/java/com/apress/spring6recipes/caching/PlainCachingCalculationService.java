package com.apress.spring6recipes.caching;

import com.apress.spring6recipes.utils.Utils;
import com.github.benmanes.caffeine.cache.Cache;

import java.math.BigDecimal;

class PlainCachingCalculationService implements CalculationService {

	private final Cache<String, BigDecimal> cache;

	public PlainCachingCalculationService(Cache<String, BigDecimal> cache) {
		this.cache = cache;
	}

	@Override
	public BigDecimal heavyCalculation(BigDecimal base, int power) {
		var key = base + "^" + power;
		return cache.get(key, k -> this.calculate(base, power));
	}

	private BigDecimal calculate(BigDecimal base, int power) {
		Utils.sleep(500);
		return base.pow(power);
	}
}
