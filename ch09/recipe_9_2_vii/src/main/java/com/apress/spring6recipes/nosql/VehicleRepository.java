package com.apress.spring6recipes.nosql;

import org.springframework.data.repository.ListCrudRepository;

public interface VehicleRepository extends ListCrudRepository<Vehicle, String> {

	Vehicle findByVehicleNo(String vehicleNo);
}
