package com.apress.spring6recipes.calculator;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Order(1)
public class CalculatorLoggingAspect {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Before("execution(* *.*(..))")
	public void logBefore(JoinPoint joinPoint) {
		log.info("The method {}() begins with {}", joinPoint.getSignature().getName(),
				Arrays.toString(joinPoint.getArgs()));
	}
}
