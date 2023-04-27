package com.apress.spring6recipes.nosql;

import com.couchbase.client.java.Cluster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.CouchbaseClientFactory;
import org.springframework.data.couchbase.SimpleCouchbaseClientFactory;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.core.convert.CouchbaseConverter;
import org.springframework.data.couchbase.core.convert.MappingCouchbaseConverter;

@Configuration
public class CouchbaseConfiguration {

	@Bean
	public Cluster cluster() {
		return Cluster.connect("couchbase://127.0.0.1", "s6r-user", "s6r-password");
	}

	@Bean
	public CouchbaseClientFactory couchbaseClientFactory(Cluster cluster) {
		return new SimpleCouchbaseClientFactory(cluster, "vehicles", null);
	}

	@Bean
	public CouchbaseConverter couchbaseConverter() {
		return new MappingCouchbaseConverter();
	}

	@Bean
	public CouchbaseTemplate couchbaseTemplate(CouchbaseClientFactory ccf,
																						 CouchbaseConverter couchbaseConverter) {
		return new CouchbaseTemplate(ccf, couchbaseConverter);
	}

	@Bean
	public CouchbaseVehicleRepository vehicleRepository(CouchbaseTemplate template) {
		return new CouchbaseVehicleRepository(template);
	}
}
