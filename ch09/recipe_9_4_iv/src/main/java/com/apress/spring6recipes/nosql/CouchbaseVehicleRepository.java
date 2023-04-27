package com.apress.spring6recipes.nosql;

import org.springframework.data.couchbase.core.CouchbaseTemplate;

class CouchbaseVehicleRepository implements VehicleRepository {

	private final CouchbaseTemplate couchbase;

	public CouchbaseVehicleRepository(CouchbaseTemplate couchbase) {
		this.couchbase = couchbase;
	}

	@Override
	public void save(Vehicle vehicle) {
		couchbase.upsertById(Vehicle.class).one(vehicle);
	}

	@Override
	public void delete(Vehicle vehicle) {
		couchbase.removeById(Vehicle.class).one(vehicle.getVehicleNo());
	}

	@Override
	public Vehicle findByVehicleNo(String vehicleNo) {
		return couchbase.findById(Vehicle.class).one(vehicleNo);
	}
}
