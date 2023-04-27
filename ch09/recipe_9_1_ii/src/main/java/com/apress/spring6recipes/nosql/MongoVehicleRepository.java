package com.apress.spring6recipes.nosql;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import jakarta.annotation.PreDestroy;
import org.bson.Document;

import java.util.List;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Filters.eq;

public class MongoVehicleRepository implements VehicleRepository {

	private final MongoClient mongo;
	private final String collectionName;
	private final String databaseName;

	public MongoVehicleRepository(MongoClient mongo, String databaseName, String collectionName) {
		this.mongo = mongo;
		this.databaseName = databaseName;
		this.collectionName = collectionName;
	}

	@Override
	public long count() {
		return getCollection().countDocuments();
	}

	@Override
	public void save(Vehicle vehicle) {
		var dbVehicle = transform(vehicle);
		getCollection().insertOne(dbVehicle);
	}

	@Override
	public void delete(Vehicle vehicle) {
		getCollection().deleteOne(eq("vehicleNo", vehicle.vehicleNo()));
	}

	@Override
	public List<Vehicle> findAll() {
		return StreamSupport.stream(getCollection().find().spliterator(), false)
						.map(this::transform)
						.toList();
	}

	@Override
	public Vehicle findByVehicleNo(String vehicleNo) {
		return transform(getCollection().find(eq("vehicleNo", vehicleNo)).first());
	}

	private MongoCollection<Document> getCollection() {
		return mongo.getDatabase(databaseName).getCollection(collectionName);
	}

	private Vehicle transform(Document dbVehicle) {
		if (dbVehicle == null) {
			return null;
		}
		return new Vehicle(
						dbVehicle.getString("vehicleNo"),
						dbVehicle.getString("color"),
						dbVehicle.getInteger("wheel"),
						dbVehicle.getInteger("seat"));
	}

	private Document transform(Vehicle vehicle) {
		return new Document("vehicleNo", vehicle.vehicleNo())
						.append("color", vehicle.color())
						.append("wheel", vehicle.wheel())
						.append("seat", vehicle.seat());
	}

	@PreDestroy
	public void cleanUp() {
		mongo.getDatabase(databaseName).drop();
	}
}
