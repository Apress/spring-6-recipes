package com.apress.spring6recipes.caching;

import com.apress.spring6recipes.utils.Utils;

import java.math.BigDecimal;

class PlainCalculationService implements CalculationService {

	@Override
	public BigDecimal heavyCalculation(BigDecimal base, int power) {
		return calculate(base, power);
	}

	private BigDecimal calculate(BigDecimal base, int power) {
		Utils.sleep(500);
		return base.pow(power);
	}
}
