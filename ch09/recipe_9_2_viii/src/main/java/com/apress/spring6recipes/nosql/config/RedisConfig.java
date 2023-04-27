package com.apress.spring6recipes.nosql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.apress.spring6recipes.nosql.RedisVehicleRepository;
import com.apress.spring6recipes.nosql.Vehicle;

@Configuration
public class RedisConfig {

	@Bean
	public ReactiveRedisTemplate<String, Vehicle> redisTemplate(
					ReactiveRedisConnectionFactory connectionFactory) {

		RedisSerializationContext.RedisSerializationContextBuilder<String, Vehicle> builder =
						RedisSerializationContext.newSerializationContext(new StringRedisSerializer());
		var serializer = new Jackson2JsonRedisSerializer<>(Vehicle.class);
		RedisSerializationContext<String, Vehicle> context = builder.hashValue(serializer).build();

		return new ReactiveRedisTemplate<>(connectionFactory, context);
	}

	@Bean
	public ReactiveRedisConnectionFactory redisConnectionFactory() {
		return new LettuceConnectionFactory("localhost", 6379);
	}

	@Bean
	public RedisVehicleRepository vehicleRepository(ReactiveRedisTemplate<String, Vehicle> redis) {
		return new RedisVehicleRepository(redis);
	}

}


