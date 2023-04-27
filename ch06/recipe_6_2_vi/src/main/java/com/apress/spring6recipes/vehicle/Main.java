package com.apress.spring6recipes.vehicle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.spring6recipes.vehicle.config.VehicleConfiguration;

public class Main {

	public static void main(String[] args) throws Exception {
		var cfg = VehicleConfiguration.class;
		try (var ctx = new AnnotationConfigApplicationContext(cfg)) {

			var vehicleDao = ctx.getBean(VehicleDao.class);
			var count = vehicleDao.countAll();
			System.out.println("Vehicle Count: " + count);
			var color = vehicleDao.getColor("TEM0001");
			System.out.println("Color for [TEM0001]: " + color);
		}
	}
}
