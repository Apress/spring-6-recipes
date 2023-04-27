package com.apress.spring6recipes.nosql;

import java.util.Map;

import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ScanOptions;

import jakarta.annotation.PreDestroy;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class RedisVehicleRepository implements VehicleRepository{

	private static final String DB_NAME = "vehicles";

	private final ReactiveRedisTemplate<String, Vehicle> redis;

	public RedisVehicleRepository(ReactiveRedisTemplate<String, Vehicle> redis) {
		this.redis = redis;
	}

	@Override
	public Mono<Long> count() {
		return redis.opsForHash().size(DB_NAME);
	}

	@Override
	public Mono<Void> save(Vehicle vehicle) {
		return redis.opsForHash().put(DB_NAME, vehicle.vehicleNo(), vehicle).then();
	}

	@Override
	public Mono<Void> delete(Vehicle vehicle) {
		return redis.opsForHash().remove(DB_NAME, vehicle.vehicleNo()).then();
	}

	@Override
	public Flux<Vehicle> findAll() {
		return redis.opsForHash().scan(DB_NAME, ScanOptions.NONE)
							.map(Map.Entry::getValue)
							.cast(Vehicle.class);
	}

	@Override
	public Mono<Vehicle> findByVehicleNo(String vehicleNo) {
		return redis.opsForHash().get(DB_NAME, vehicleNo).cast(Vehicle.class);
	}

	@PreDestroy
	public void cleanUp() {
		findAll().map(this::delete).subscribe();
	}
}
