package com.apress.spring6recipes.vehicle;

import static org.springframework.data.relational.core.query.Criteria.where;
import static org.springframework.data.relational.core.query.Query.query;

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;

import io.r2dbc.spi.Row;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class R2dbcVehicleDao implements VehicleDao {

	private final R2dbcEntityTemplate template;

	public R2dbcVehicleDao(R2dbcEntityTemplate template) {
		this.template = template;
	}

	@Override
	public Mono<Vehicle> save(Vehicle vehicle) {
		return template.insert(vehicle);
	}

	@Override
	public Mono<Vehicle> findByVehicleNo(String vehicleNo) {
		var query = query(where("vehicleNo").is(vehicleNo));
		return template.selectOne(query, Vehicle.class);
	}

	@Override
	public Flux<Vehicle> findAll() {
		return template.select(Vehicle.class).all();
	}

	@Override
	public Mono<Void> delete(Vehicle vehicle) {
		return template.delete(vehicle).then();
	}
}
