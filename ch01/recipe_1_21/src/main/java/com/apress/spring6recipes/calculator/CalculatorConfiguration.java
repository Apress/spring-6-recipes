package com.apress.spring6recipes.calculator;

import org.aspectj.lang.Aspects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
@ComponentScan
public class CalculatorConfiguration {

	@Bean
	public ComplexCachingAspect complexCachingAspect() {

		var cache = new ConcurrentHashMap<String, Complex>();
		cache.put("2,3", new Complex(2, 3));
		cache.put("3,5", new Complex(3, 5));

		var complexCachingAspect = Aspects.aspectOf(ComplexCachingAspect.class);
		complexCachingAspect.setCache(cache);
		return complexCachingAspect;
	}
}
