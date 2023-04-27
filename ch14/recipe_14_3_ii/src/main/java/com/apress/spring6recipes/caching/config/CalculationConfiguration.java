package com.apress.spring6recipes.caching.config;

import com.apress.spring6recipes.caching.CalculationService;
import com.apress.spring6recipes.caching.PlainCalculationService;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

import java.time.Duration;

@Configuration
@EnableLoadTimeWeaving
@EnableCaching(mode = AdviceMode.ASPECTJ)
public class CalculationConfiguration {

	@Bean
	public CacheManager cacheManager() {
		var caffeine = Caffeine.newBuilder()
						.maximumSize(1000)
						.expireAfterWrite(Duration.ofMinutes(5));
		var cacheManager = new CaffeineCacheManager();
		cacheManager.setCaffeine(caffeine);
		return cacheManager;
	}

	@Bean
	public CalculationService calculationService() {
		return new PlainCalculationService();
	}
}
