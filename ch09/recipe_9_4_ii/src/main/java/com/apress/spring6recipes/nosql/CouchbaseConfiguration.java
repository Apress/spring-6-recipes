package com.apress.spring6recipes.nosql;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CouchbaseConfiguration {

	@Bean
	public Cluster cluster() {
		return Cluster.connect("couchbase://127.0.0.1", "s6r-user", "s6r-password");
	}

	@Bean
	public Bucket bucket(Cluster cluster) {
		return cluster.bucket("vehicles");
	}

	@Bean
	public CouchbaseVehicleRepository vehicleRepository(Bucket bucket) {
		return new CouchbaseVehicleRepository(bucket);
	}
}
