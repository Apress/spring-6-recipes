package com.apress.spring6recipes.nosql;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.spring6recipes.nosql.config.RedisConfig;

public class Main {

	private static final String COUNT = "Number of Vehicles: %d%n";

	public static void main(String[] args) {
		var cfg = RedisConfig.class;
		try (var ctx = new AnnotationConfigApplicationContext(cfg)) {
			var repository = ctx.getBean(VehicleRepository.class);

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
			repository.deleteAll();
		}
	}
}
