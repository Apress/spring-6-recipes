package com.apress.spring6recipes.nosql;

import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.data.neo4j.core.mapping.Neo4jMappingContext;
import org.springframework.data.neo4j.core.transaction.Neo4jTransactionManager;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:/application.properties")
@EnableTransactionManagement
@ComponentScan
@EnableNeo4jRepositories
public class StarwarsConfig {

	@Bean
	public Driver driver(@Value("${neo4j.url}") String url) {
		return GraphDatabase.driver(url);
	}

	@Bean
	public Neo4jClient neo4jClient(Driver driver) {
		return Neo4jClient.create(driver);
	}

	@Bean
	public Neo4jTemplate neo4jTemplate(Neo4jClient neo4jClient) {
		return new Neo4jTemplate(neo4jClient);
	}

	@Bean
	public Neo4jTransactionManager transactionManager(Driver driver) {
		return Neo4jTransactionManager.with(driver).build();
	}

	@Bean
	public Neo4jMappingContext neo4jMappingContext() {
		return new Neo4jMappingContext();
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer pspc() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
