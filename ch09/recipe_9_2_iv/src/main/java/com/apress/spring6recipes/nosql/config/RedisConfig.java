package com.apress.spring6recipes.nosql.config;

import static redis.clients.jedis.Protocol.DEFAULT_PORT;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.spring6recipes.nosql.RedisVehicleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.Jedis;

@Configuration
public class RedisConfig {

	@Bean
	public Jedis jedis() {
		return new Jedis("localhost", DEFAULT_PORT);
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	@Bean
	public RedisVehicleRepository vehicleRepository(Jedis jedis, ObjectMapper om) {
		return new RedisVehicleRepository(jedis, om);
	}
}


