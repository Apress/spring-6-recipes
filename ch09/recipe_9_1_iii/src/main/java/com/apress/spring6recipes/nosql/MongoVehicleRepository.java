package com.apress.spring6recipes.nosql;

import jakarta.annotation.PreDestroy;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class MongoVehicleRepository implements VehicleRepository {

	private final MongoTemplate mongo;
	private final String collectionName;

	public MongoVehicleRepository(MongoTemplate mongo, String collectionName) {
		this.mongo = mongo;
		this.collectionName = collectionName;
	}

	@Override
	public long count() {
		return mongo.count(new Query(), collectionName);
	}

	@Override
	public void save(Vehicle vehicle) {
		mongo.save(vehicle, collectionName);
	}

	@Override
	public void delete(Vehicle vehicle) {
		mongo.remove(vehicle, collectionName);
	}

	@Override
	public List<Vehicle> findAll() {
		return mongo.findAll(Vehicle.class, collectionName);
	}

	@Override
	public Vehicle findByVehicleNo(String vehicleNo) {
		var query = new Query(where("vehicleNo").is(vehicleNo));
		return mongo.findOne(query, Vehicle.class, collectionName);
	}

	@PreDestroy
	public void cleanUp() {
		mongo.execute(db -> {
			db.drop();
			return null;
		});
	}
}