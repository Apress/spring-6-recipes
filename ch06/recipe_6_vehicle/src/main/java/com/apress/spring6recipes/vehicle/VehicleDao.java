package com.apress.spring6recipes.vehicle;

import java.util.Collection;
import java.util.List;

public interface VehicleDao {

	void insert(Vehicle vehicle);
	void update(Vehicle vehicle);
	void delete(Vehicle vehicle);
	Vehicle findByVehicleNo(String vehicleNo);
	List<Vehicle> findAll();

	/*
	 * The naive default implemenation is to call the single element insert method.
	 */
	default void insert(Collection<Vehicle> vehicles) {
		vehicles.forEach(this::insert);
	}

	/*
	 * These methods have a default implementation to throw an exception they are used
	 * later in the chapter and at first aren't relevant.
	 */
	default String getColor(String vehicleNo) {
		throw new IllegalStateException("Method is not implemented!");
	}

	default int countAll() {
		throw new IllegalStateException("Method is not implemented!");
	}
}
