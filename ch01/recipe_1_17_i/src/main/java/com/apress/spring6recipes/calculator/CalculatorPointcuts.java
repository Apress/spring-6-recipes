package com.apress.spring6recipes.calculator;

import org.aspectj.lang.annotation.Pointcut;

public class CalculatorPointcuts {

	@Pointcut("@annotation(com.apress.spring6recipes.calculator.LoggingRequired)")
	public void loggingOperation() {
	}
}
