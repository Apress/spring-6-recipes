package com.apress.spring6recipes.caching.config;

import com.apress.spring6recipes.caching.CalculationService;
import com.apress.spring6recipes.caching.PlainCachingCalculationService;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.Duration;

@Configuration
public class CalculationConfiguration {

	@Bean
	public Cache<String, BigDecimal> calculationsCache() {
		return Caffeine.newBuilder()
						.maximumSize(1000)
						.expireAfterWrite(Duration.ofMinutes(5)).build();
	}

	@Bean
	public CalculationService calculationService(Cache<String, BigDecimal> cache) {
		return new PlainCachingCalculationService(cache);
	}
}
