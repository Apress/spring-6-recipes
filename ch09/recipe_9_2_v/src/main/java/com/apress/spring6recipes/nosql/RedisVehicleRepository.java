package com.apress.spring6recipes.nosql;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;

import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.annotation.PreDestroy;

public class RedisVehicleRepository implements VehicleRepository{

	private static final String DB_NAME = "vehicles";

	private final RedisTemplate<String, Vehicle> redis;

	public RedisVehicleRepository(RedisTemplate<String, Vehicle> redis) {
		this.redis = redis;
	}

	@Override
	public long count() {
		return redis.opsForHash().size(DB_NAME);
	}

	@Override
	public void save(Vehicle vehicle) {
		redis.opsForHash().put(DB_NAME, vehicle.vehicleNo(), vehicle);
	}

	@Override
	public void delete(Vehicle vehicle) {
		redis.opsForHash().delete(DB_NAME, vehicle.vehicleNo());
	}

	@Override
	public List<Vehicle> findAll() {
		try (var cursor = redis.opsForHash().scan(DB_NAME, ScanOptions.NONE)) {
			return cursor.stream()
							.map(Map.Entry::getValue)
							.map(Vehicle.class::cast).toList();
		}
	}

	@Override
	public Vehicle findByVehicleNo(String vehicleNo) {
		return (Vehicle) redis.opsForHash().get(DB_NAME, vehicleNo);
	}

	@PreDestroy
	public void cleanUp() {
		findAll().forEach(this::delete);
	}
}
