package com.apress.spring6recipes.caching.config;

import com.apress.spring6recipes.caching.CustomerRepository;
import com.apress.spring6recipes.caching.MapBasedCustomerRepository;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@EnableCaching
public class CustomerConfiguration {

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
	public CustomerRepository customerRepository() {
		return new MapBasedCustomerRepository();
	}
}
