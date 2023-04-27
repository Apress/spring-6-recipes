package com.apress.spring6recipes.calculator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.context.weaving.AspectJWeavingEnabler;

@Configuration
@EnableSpringConfigured
@EnableLoadTimeWeaving
@ComponentScan
public class CalculatorConfiguration {

	@Bean
	public static AspectJWeavingEnabler aspectJWeavingEnabler() {
		return new AspectJWeavingEnabler();
	}

}
