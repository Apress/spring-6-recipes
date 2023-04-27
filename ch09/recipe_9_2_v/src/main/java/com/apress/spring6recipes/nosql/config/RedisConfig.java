package com.apress.spring6recipes.nosql.config;

import com.apress.spring6recipes.nosql.RedisVehicleRepository;
import com.apress.spring6recipes.nosql.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

	@Bean
	public RedisTemplate<String, Vehicle> redisTemplate(
					RedisConnectionFactory connectionFactory) {
		var template = new RedisTemplate<String, Vehicle>();
		template.setConnectionFactory(connectionFactory);
		return template;
	}

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		return new JedisConnectionFactory();
	}

	@Bean
	public RedisVehicleRepository vehicleRepository(RedisTemplate<String, Vehicle> redis) {
		return new RedisVehicleRepository(redis);
	}
}


