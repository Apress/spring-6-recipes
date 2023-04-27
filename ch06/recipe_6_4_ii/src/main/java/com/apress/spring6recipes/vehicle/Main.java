package com.apress.spring6recipes.vehicle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.spring6recipes.vehicle.config.VehicleConfiguration;

/**
 * Created by marten on 24-03-14.
 */
public class Main {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = new AnnotationConfigApplicationContext(VehicleConfiguration.class);

		VehicleDao vehicleDao = context.getBean(VehicleDao.class);
		Vehicle vehicle = new Vehicle("TEM0042", "Red", 4, 4);
		vehicleDao.insert(vehicle);

		vehicle = vehicleDao.findByVehicleNo("TEM0042");
		System.out.println("Vehicle No: " + vehicle.getVehicleNo());
		System.out.println("Color: " + vehicle.getColor());
		System.out.println("Wheel: " + vehicle.getWheel());
		System.out.println("Seat: " + vehicle.getSeat());

	}

}
