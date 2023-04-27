package com.apress.spring6recipes.calculator;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class CalculatorLoggingAspect {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Before("execution(* *.*(..))")
	public void logBefore(JoinPoint joinPoint) {
		var name = joinPoint.getSignature().getName();
		var args = Arrays.toString(joinPoint.getArgs());
		log.info("The method {}() begins with {} ", name, args);
	}

	@AfterReturning(pointcut = "execution(* *.*(..))", returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		var name = joinPoint.getSignature().getName();
		log.info("The method {}() ends with {}", name, result);
	}

	@AfterThrowing(
					pointcut = "execution(* *.*(..))",
					throwing = "ex")
	public void logAfterThrowing(JoinPoint joinPoint, IllegalArgumentException ex) {
		var args = Arrays.toString(joinPoint.getArgs());
		var name = joinPoint.getSignature().getName();
		log.error("Illegal argument {} in {}()", args, name);
	}
}
