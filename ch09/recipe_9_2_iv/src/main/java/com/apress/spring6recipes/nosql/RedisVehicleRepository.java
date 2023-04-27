package com.apress.spring6recipes.nosql;

import java.util.List;

import org.springframework.util.SerializationUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PreDestroy;
import redis.clients.jedis.Jedis;

public class RedisVehicleRepository implements VehicleRepository{

	private static final String DB_NAME = "vehicles";

	private final Jedis jedis;
	private final ObjectMapper mapper;

	public RedisVehicleRepository(Jedis jedis, ObjectMapper mapper) {
		this.jedis = jedis;
		this.mapper = mapper;
	}

	@Override
	public long count() {
		return jedis.hkeys(DB_NAME).size();
	}

	@Override
	public void save(Vehicle vehicle) {
		try {
			var vehicleJson = mapper.writeValueAsString(vehicle);
			jedis.hset(DB_NAME, vehicle.vehicleNo(), vehicleJson);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(Vehicle vehicle) {
		jedis.hdel(DB_NAME, vehicle.vehicleNo());
	}

	@Override
	public List<Vehicle> findAll() {
		return jedis.hkeys(DB_NAME).stream()
						.map(this::findByVehicleNo).toList();
	}

	@Override
	public Vehicle findByVehicleNo(String vehicleNo) {
		var vehicleJson = jedis.hget(DB_NAME, vehicleNo);
		try {
			return mapper.readValue(vehicleJson, Vehicle.class);
		} catch (JsonProcessingException ex) {
			throw new RuntimeException(ex);
		}
	}

	@PreDestroy
	public void cleanUp() {
		findAll().forEach(this::delete);
	}
}
