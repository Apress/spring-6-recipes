package com.apress.spring6recipes.caching;

import com.apress.spring6recipes.utils.Utils;
import org.springframework.cache.Cache;

import java.math.BigDecimal;

public class PlainCachingCalculationService implements CalculationService {

	private final Cache cache;

	public PlainCachingCalculationService(Cache cache) {
		this.cache = cache;
	}

	@Override
	public BigDecimal heavyCalculation(BigDecimal base, int power) {
		var key = base + "^" + power;
		var result = cache.get(key, BigDecimal.class);
		if (result != null) {
			return result;
		}
		Utils.sleep(500);
		var calculatedResult = base.pow(power);
		cache.putIfAbsent(key, calculatedResult);
		return calculatedResult;
	}
}
