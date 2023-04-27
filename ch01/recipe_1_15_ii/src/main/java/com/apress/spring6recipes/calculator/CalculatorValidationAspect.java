package com.apress.spring6recipes.calculator;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(0)
public class CalculatorValidationAspect {

	@Before("execution(* *.*(double, double))")
	public void validateBefore(JoinPoint joinPoint) {
		for (var arg : joinPoint.getArgs()) {
			validate((Double) arg);
		}
	}

	private void validate(double a) {
		if (a < 0) {
			throw new IllegalArgumentException("Positive numbers only");
		}
	}
}
