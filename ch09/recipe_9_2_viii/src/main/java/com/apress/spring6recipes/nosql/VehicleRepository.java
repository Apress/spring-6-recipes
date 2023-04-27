package com.apress.spring6recipes.nosql;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VehicleRepository {

	Mono<Long> count();
	Mono<Void> save(Vehicle vehicle);
	Mono<Void> delete(Vehicle vehicle);
	Flux<Vehicle> findAll();
	Mono<Vehicle> findByVehicleNo(String vehicleNo);
}
