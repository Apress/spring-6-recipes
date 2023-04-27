package com.apress.spring6recipes.calculator;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class CalculatorLoggingAspect {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Around("execution(* *.*(..))")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		var name = joinPoint.getSignature().getName();
		var args = Arrays.toString(joinPoint.getArgs());
		log.info("The method {}() begins with {}.", name, args);
		try {
			var result = joinPoint.proceed();
			log.info("The method {}() ends with {}.", name, result);
			return result;
		}
		catch (IllegalArgumentException ex) {
			log.error("Illegal argument {} in {}()", args, name);
			throw ex;
		}
	}
}
