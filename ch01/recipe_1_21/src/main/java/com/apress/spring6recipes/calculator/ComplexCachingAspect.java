package com.apress.spring6recipes.calculator;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
public class ComplexCachingAspect {

	private final Map<String, Complex> cache = new ConcurrentHashMap<>();

	public void setCache(Map<String, Complex> cache) {
		this.cache.clear();
		this.cache.putAll(cache);
	}

	@Around("call(com.apress.spring6recipes.calculator.Complex.new(int, int)) && args(a,b)")
	public Object cacheAround(ProceedingJoinPoint pjp, int a, int b) throws Throwable {
		var key = a + "," + b;
		return cache.compute(key, (key1, val) -> checkCacheOrCalculate(pjp, key1, val));
	}

	private Complex checkCacheOrCalculate(ProceedingJoinPoint pjp, String key, Complex current) {
		if (current == null) {
			try {
				System.out.println("Cache MISS for (" + key + ")");
				return (Complex) pjp.proceed();
			} catch (Throwable ex) {
				throw new IllegalStateException(ex);
			}
		} else {
			System.out.println("Cache HIT for (" + key + ")");
			return current;
		}
	}
}
