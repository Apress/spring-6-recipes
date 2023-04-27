package com.apress.spring6recipes.calculator;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CalculatorValidationAspect implements Ordered {

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

	@Override
	public int getOrder() {
		return 0;
	}
}
