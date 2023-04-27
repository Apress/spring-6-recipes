package com.apress.spring6recipes.caching;

import java.math.BigDecimal;

public interface CalculationService {

	BigDecimal heavyCalculation(BigDecimal base, int power);

}
