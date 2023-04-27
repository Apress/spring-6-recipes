package com.apress.spring6recipes.nosql;

import com.couchbase.client.java.Bucket;

class CouchbaseVehicleRepository implements VehicleRepository {

	private final Bucket bucket;

	public CouchbaseVehicleRepository(Bucket bucket) {
		this.bucket = bucket;
	}

	@Override
	public void save(Vehicle vehicle) {
		bucket.defaultCollection().upsert(vehicle.vehicleNo(), vehicle);
	}

	@Override
	public void delete(Vehicle vehicle) {
		bucket.defaultCollection().remove(vehicle.vehicleNo());
	}

	@Override
	public Vehicle findByVehicleNo(String vehicleNo) {
		return bucket.defaultCollection().get(vehicleNo).contentAs(Vehicle.class);
	}
}
