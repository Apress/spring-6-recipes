package com.apress.spring6recipes.nosql;

import java.util.List;

import org.springframework.util.SerializationUtils;

import jakarta.annotation.PreDestroy;
import redis.clients.jedis.Jedis;

public class RedisVehicleRepository implements VehicleRepository {

	private static final String DB_NAME = "vehicles";

	private final Jedis jedis;

	public RedisVehicleRepository(Jedis jedis) {
		this.jedis = jedis;
	}

	@Override
	public long count() {
		return jedis.hkeys(DB_NAME.getBytes()).size();
	}

	@Override
	public void save(Vehicle vehicle) {
		var key = vehicle.vehicleNo();
		var vehicleArray = SerializationUtils.serialize(vehicle);
		jedis.hset(DB_NAME.getBytes(), key.getBytes(), vehicleArray);
	}

	@Override
	public void delete(Vehicle vehicle) {
		jedis.hdel(DB_NAME.getBytes(), vehicle.vehicleNo().getBytes());
	}

	@Override
	public List<Vehicle> findAll() {
		return jedis.hkeys(DB_NAME).stream()
						.map(this::findByVehicleNo).toList();
	}

	@Override
	public Vehicle findByVehicleNo(String vehicleNo) {
		var vehicleArray = jedis.hget(DB_NAME.getBytes(), vehicleNo.getBytes());
		return (Vehicle) SerializationUtils.deserialize(vehicleArray);
	}

	@PreDestroy
	public void cleanUp() {
		findAll().forEach(this::delete);
	}
}
