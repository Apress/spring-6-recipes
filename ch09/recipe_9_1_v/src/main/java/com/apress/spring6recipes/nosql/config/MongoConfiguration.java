package com.apress.spring6recipes.nosql.config;

import com.mongodb.client.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.apress.spring6recipes.nosql")
public class MongoConfiguration {

	public static final String DB_NAME = "vehicledb";

	@Bean
	public MongoTemplate mongoTemplate(MongoClient mongo) {
		return new MongoTemplate(mongo, DB_NAME);
	}

	@Bean
	public MongoClientFactoryBean mongoFactoryBean() {
		return new MongoClientFactoryBean();
	}
}