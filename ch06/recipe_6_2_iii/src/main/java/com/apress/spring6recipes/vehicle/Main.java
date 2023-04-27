package com.apress.spring6recipes.vehicle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.spring6recipes.vehicle.config.VehicleConfiguration;

public class Main {

	public static void main(String[] args) throws Exception {
		var cfg = VehicleConfiguration.class;
		try (var ctx = new AnnotationConfigApplicationContext(cfg)) {

			var vehicleDao = ctx.getBean(VehicleDao.class);
			var vehicle = new Vehicle("TEM0023", "Red", 4, 4);
			vehicleDao.insert(vehicle);

			vehicle = vehicleDao.findByVehicleNo("TEM0023");
			System.out.println("Vehicle No: " + vehicle.getVehicleNo());
			System.out.println("Color: " + vehicle.getColor());
			System.out.println("Wheel: " + vehicle.getWheel());
			System.out.println("Seat: " + vehicle.getSeat());
		}
	}

}
