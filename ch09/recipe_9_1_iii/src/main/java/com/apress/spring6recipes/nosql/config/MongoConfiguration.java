package com.apress.spring6recipes.nosql.config;

import com.apress.spring6recipes.nosql.MongoVehicleRepository;
import com.mongodb.client.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfiguration {

	private static final String DB_NAME = "vehicledb";
	private static final String COLLECTION_NAME = "vehicles";

	@Bean
	public MongoTemplate mongo(MongoClient mongo) throws Exception {
		return new MongoTemplate(mongo, DB_NAME);
	}

	@Bean
	public MongoClientFactoryBean mongoFactoryBean() {
		return new MongoClientFactoryBean();
	}

	@Bean
	public MongoVehicleRepository vehicleRepository(MongoTemplate mongo) {
		return new MongoVehicleRepository(mongo, COLLECTION_NAME);
	}
}