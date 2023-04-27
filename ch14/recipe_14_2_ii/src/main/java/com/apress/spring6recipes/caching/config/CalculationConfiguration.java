package com.apress.spring6recipes.caching.config;

import com.apress.spring6recipes.caching.CalculationService;
import com.apress.spring6recipes.caching.PlainCachingCalculationService;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
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
	public CalculationService calculationService(CacheManager cacheManager) {
		var cache = cacheManager.getCache("calculations");
		return new PlainCachingCalculationService(cache);
	}
}
