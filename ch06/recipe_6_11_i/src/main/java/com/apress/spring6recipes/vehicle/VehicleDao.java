package com.apress.spring6recipes.vehicle;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VehicleDao {

	Mono<Vehicle> save(Vehicle vehicle);
	Mono<Vehicle> findByVehicleNo(String vehicleNo);
	Flux<Vehicle> findAll();
	Mono<Void> delete(Vehicle vehicle);
}
