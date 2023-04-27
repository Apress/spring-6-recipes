package com.apress.spring6recipes.vehicle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.spring6recipes.vehicle.config.VehicleConfiguration;

public class Main {

	public static void main(String[] args) throws Exception {
		try (var ctx = new AnnotationConfigApplicationContext(VehicleConfiguration.class)) {
			var vehicleDao = ctx.getBean(VehicleDao.class);
			var vehicle = new Vehicle("TEM0001", "Red", 4, 4);
			vehicleDao.insert(vehicle);

			vehicle = vehicleDao.findByVehicleNo("TEM0001");
			System.out.println(vehicle);
		}
	}
}
