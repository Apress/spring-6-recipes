package com.apress.spring6recipes.nosql;

import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository;

public interface VehicleRepository
				extends ReactiveCouchbaseRepository<Vehicle, String> { }
