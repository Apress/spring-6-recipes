package com.apress.spring6recipes.nosql.config;

import com.apress.spring6recipes.nosql.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

@Configuration
@EnableRedisRepositories(basePackages = "com.apress.spring6recipes.nosql")
public class RedisConfig {

	@Bean
	public RedisTemplate<String, Vehicle> redisTemplate(
					RedisConnectionFactory connectionFactory) {
		var template = new RedisTemplate<String, Vehicle>();
		template.setConnectionFactory(connectionFactory);
		template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
		template.setEnableTransactionSupport(true);
		return template;
	}

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		return new JedisConnectionFactory();
	}

}


