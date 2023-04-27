package com.apress.spring6recipes.caching;

import java.math.BigDecimal;

/**
 * @author Marten Deinum
 */
public interface CalculationService {

	BigDecimal heavyCalculation(BigDecimal base, int power);

}
