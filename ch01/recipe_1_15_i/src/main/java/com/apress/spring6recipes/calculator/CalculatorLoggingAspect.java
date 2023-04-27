package com.apress.spring6recipes.calculator;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class CalculatorLoggingAspect implements Ordered {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Before("execution(* *.*(..))")
	public void logJoinPoint(JoinPoint joinPoint) {
		log.info("Join point kind : {}", joinPoint.getKind());
		log.info("Signature declaring type : {}", joinPoint.getSignature().getDeclaringTypeName());
		log.info("Signature name : {}", joinPoint.getSignature().getName());
		log.info("Arguments : {}", Arrays.toString(joinPoint.getArgs()));
		log.info("Target class : {}", joinPoint.getTarget().getClass().getName());
		log.info("This class : {}", joinPoint.getThis().getClass().getName());
	}

	@Override
	public int getOrder() {
		return 1;
	}

}
