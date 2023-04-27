package com.apress.spring6recipes.nosql;

import com.mongodb.client.MongoClients;

public class Main {

	private static final String DB_NAME = "vehicledb";
	private static final String COUNT = "Number of Vehicles: %d%n";

	public static void main(String[] args) {
		try (var mongo = MongoClients.create()) {

			var repository = new MongoVehicleRepository(mongo, DB_NAME, "vehicles");

			System.out.printf(COUNT, repository.count());

			repository.save(new Vehicle("TEM0001", "RED", 4, 4));
			repository.save(new Vehicle("TEM0002", "RED", 4, 4));

			System.out.printf(COUNT, repository.count());

			var v = repository.findByVehicleNo("TEM0001");

			System.out.println(v);

			var vehicleList = repository.findAll();

			System.out.printf(COUNT, vehicleList.size());
			vehicleList.forEach(System.out::println);
			System.out.printf(COUNT, repository.count());

			mongo.getDatabase(DB_NAME).drop();
		}
	}
}
