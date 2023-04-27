package com.apress.spring6recipes.calculator;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class CalculatorLoggingAspect {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Pointcut("execution(* *.*(..))")
	private void loggingOperation() {}

	@Before("loggingOperation()")
	public void logBefore(JoinPoint joinPoint) {
		log.info("The method {}() begins with {}",joinPoint.getSignature().getName()
				, Arrays.toString(joinPoint.getArgs()));
	}

	@After("loggingOperation()")
	public void logAfter(JoinPoint joinPoint) {
		log.info("The method {}() ends", joinPoint.getSignature().getName() );
	}

	@AfterReturning(
					pointcut = "loggingOperation()",
					returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		log.info("The method {}() ends with {}",joinPoint.getSignature().getName(), result);
	}

	@AfterThrowing(
					pointcut = "loggingOperation()",
					throwing = "ex")
	public void logAfterThrowing(JoinPoint joinPoint, IllegalArgumentException ex) {
		log.error("Illegal argument {} in {}()", Arrays.toString(joinPoint.getArgs()) ,joinPoint.getSignature().getName(), ex);
	}

	@Around("loggingOperation()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		var name = joinPoint.getSignature().getName();
		var args = Arrays.toString(joinPoint.getArgs());
		log.info("The method {}() begins with {}", name, args);
		try {
			var result = joinPoint.proceed();
			log.info("The method {}() ends with {}", name, result);
			return result;
		}
		catch (IllegalArgumentException ex) {
			log.error("Illegal argument {} in {}", args, name, ex);
			throw ex;
		}
	}
}
