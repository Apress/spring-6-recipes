package com.apress.spring6recipes.nosql;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		var cfg = CouchbaseConfiguration.class;
		try (var ctx = new AnnotationConfigApplicationContext(cfg)) {
			VehicleRepository vehicleRepository = ctx.getBean(VehicleRepository.class);

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
