package com.apress.spring6recipes.vehicle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.spring6recipes.vehicle.config.VehicleConfiguration;

public class Main {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = new AnnotationConfigApplicationContext(VehicleConfiguration.class);

		VehicleDao vehicleDao = context.getBean(VehicleDao.class);
		Vehicle vehicle = new Vehicle("TEM0032", "Red", 4, 4);
		vehicleDao.insert(vehicle);

		vehicle = vehicleDao.findByVehicleNo("TEM0032");
		System.out.println(vehicle);
	}

}
