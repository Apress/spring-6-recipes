package com.apress.spring6recipes.nosql;

import com.couchbase.client.java.Cluster;

public class Main {

	public static void main(String[] args) {

		try (var cluster = Cluster.connect("couchbase://127.0.0.1", "s6r-user", "s6r-password")){
			var bucket = cluster.bucket("vehicles");

			var vehicleRepository = new CouchbaseVehicleRepository(bucket);
			vehicleRepository.save(new Vehicle("TEM0001", "GREEN", 3, 1));
			vehicleRepository.save(new Vehicle("TEM0004", "RED", 4, 2));

			var v1 = vehicleRepository.findByVehicleNo("TEM0001");
			var v2 = vehicleRepository.findByVehicleNo("TEM0004");

			System.out.println("Vehicle: " + v1);
			System.out.println("Vehicle: " + v2);

			vehicleRepository.delete(v1);
			vehicleRepository.delete(v2);
		}
	}
}
