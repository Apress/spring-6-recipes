package com.apress.spring6recipes.vehicle;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VehicleDao extends ReactiveCrudRepository<Vehicle, String> {

	Mono<Vehicle> findByVehicleNo(String vehicleNo);
}
