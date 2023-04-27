package com.apress.spring6recipes.nosql;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {

		try (var ctx = new AnnotationConfigApplicationContext(CouchbaseConfiguration.class)) {
			var vehicleRepository = ctx.getBean(VehicleRepository.class);

			vehicleRepository.save(new Vehicle("TEM0001", "GREEN", 3, 1));
			vehicleRepository.save(new Vehicle("TEM0004", "RED", 4, 2));

			System.out.println("Vehicle: " + vehicleRepository.findByVehicleNo("TEM0001"));
			System.out.println("Vehicle: " + vehicleRepository.findByVehicleNo("TEM0004"));

			vehicleRepository.delete(vehicleRepository.findByVehicleNo("TEM0001"));
			vehicleRepository.delete(vehicleRepository.findByVehicleNo("TEM0004"));
		}
	}
}
