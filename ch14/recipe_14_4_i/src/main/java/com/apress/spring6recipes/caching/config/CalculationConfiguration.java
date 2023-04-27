package com.apress.spring6recipes.caching.config;

import com.apress.spring6recipes.caching.CalculationService;
import com.apress.spring6recipes.caching.CustomKeyGenerator;
import com.apress.spring6recipes.caching.PlainCalculationService;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@EnableCaching
public class CalculationConfiguration implements CachingConfigurer {

	@Bean
	@Override
	public CacheManager cacheManager() {
		var caffeine = Caffeine.newBuilder()
						.maximumSize(1000)
						.expireAfterWrite(Duration.ofMinutes(5));
		var cacheManager = new CaffeineCacheManager();
		cacheManager.setCaffeine(caffeine);
		return cacheManager;
	}

	@Bean
	@Override
	public KeyGenerator keyGenerator() {
		return new CustomKeyGenerator();
	}

	@Bean
	public CalculationService calculationService() {
		return new PlainCalculationService();
	}
}
