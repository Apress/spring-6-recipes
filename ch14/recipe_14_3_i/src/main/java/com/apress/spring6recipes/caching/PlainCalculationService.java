package com.apress.spring6recipes.caching;

import com.apress.spring6recipes.utils.Utils;
import org.springframework.cache.annotation.Cacheable;

import java.math.BigDecimal;

public class PlainCalculationService implements CalculationService {

	@Override
	@Cacheable("calculations")
	public BigDecimal heavyCalculation(BigDecimal base, int power) {
		return calculate(base, power);
	}

	private BigDecimal calculate(BigDecimal base, int power) {
		Utils.sleep(500);
		return base.pow(power);
	}
}
