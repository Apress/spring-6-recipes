package com.apress.spring6recipes.nosql.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.apress.spring6recipes.nosql")
public class MongoConfiguration extends AbstractReactiveMongoConfiguration {

	private static final String DB_NAME = "vehicledb";

	@Bean
	@Override
	public MongoClient reactiveMongoClient() {
		return MongoClients.create();
	}

	@Override
	protected String getDatabaseName() {
		return DB_NAME;
	}
}
