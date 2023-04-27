package com.apress.spring6recipes.nosql;

public interface VehicleRepository {

	void save(Vehicle vehicle);
	void delete(Vehicle vehicle);
	Vehicle findByVehicleNo(String vehicleNo);
}
