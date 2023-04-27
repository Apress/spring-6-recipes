package com.apress.spring6recipes.vehicle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.spring6recipes.vehicle.config.VehicleConfiguration;

public class Main {

	public static void main(String[] args) throws Exception {
		var cfg = VehicleConfiguration.class;
		try (var context = new AnnotationConfigApplicationContext(cfg)) {

			var vehicleDao = context.getBean(VehicleDao.class);
			var vehicles = vehicleDao.findAll();
			for (var vehicle : vehicles) {
				System.out.println("Vehicle No: " + vehicle.getVehicleNo());
				System.out.println("Color: " + vehicle.getColor());
				System.out.println("Wheel: " + vehicle.getWheel());
				System.out.println("Seat: " + vehicle.getSeat());
			}
		}
	}
}
