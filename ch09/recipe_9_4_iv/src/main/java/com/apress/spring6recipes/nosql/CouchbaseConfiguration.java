package com.apress.spring6recipes.nosql;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.CouchbaseTemplate;

@Configuration
public class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {

	@Override
	public String getConnectionString() {
		return "couchbase://127.0.0.1";
	}

	@Override
	public String getUserName() {
		return "s6r-user";
	}

	@Override
	public String getPassword() {
		return "s6r-password";
	}

	@Override
	public String getBucketName() {
		return "vehicles";
	}

	@Bean
	public CouchbaseVehicleRepository vehicleRepository(CouchbaseTemplate template) {
		return new CouchbaseVehicleRepository(template);
	}
}
