package com.apress.spring6recipes.calculator;

import org.aspectj.lang.JoinPoint;
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
	public void logJoinPoint(JoinPoint jp) {
		log.info("Join point kind : {}", jp.getKind());
		log.info("Signature declaring type : {}", jp.getSignature().getDeclaringTypeName());
		log.info("Signature name : {}", jp.getSignature().getName());
		log.info("Arguments : {}", Arrays.toString(jp.getArgs()));
		log.info("Target class : {}", jp.getTarget().getClass().getName());
		log.info("This class : {}", jp.getThis().getClass().getName());
	}
}
