package com.apress.spring6recipes.nosql.config;

import com.apress.spring6recipes.nosql.MongoVehicleRepository;
import com.apress.spring6recipes.nosql.VehicleRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfiguration {

	private static final String DB_NAME = "vehicledb";
  private static final String COLLECTION_NAME = "vehicles";

	@Bean
	public MongoClient mongo() {
		return MongoClients.create();
	}

	@Bean
	public MongoVehicleRepository vehicleRepository(MongoClient mongo) {
		return new MongoVehicleRepository(mongo, DB_NAME, COLLECTION_NAME);
	}
}