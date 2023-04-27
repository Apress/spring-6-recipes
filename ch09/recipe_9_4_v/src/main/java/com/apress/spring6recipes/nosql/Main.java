package com.apress.spring6recipes.nosql;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {

		try (var context = new AnnotationConfigApplicationContext(CouchbaseConfiguration.class)) {
			VehicleRepository vehicleRepository = context.getBean(VehicleRepository.class);

			vehicleRepository.save(new Vehicle("TEM0001", "GREEN", 3, 1));
			vehicleRepository.save(new Vehicle("TEM0004", "RED", 4, 2));

			vehicleRepository.findById("TEM0001").ifPresent(System.out::println);
			vehicleRepository.findById("TEM0004").ifPresent(System.out::println);

			vehicleRepository.deleteById("TEM0001");
			vehicleRepository.deleteById("TEM0004");
		}
	}
}
