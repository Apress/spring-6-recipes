package com.apress.spring6recipes.nosql;

import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:/application.properties")
public class StarwarsConfig {

	@Bean
	public Driver driver(@Value("${neo4j.url}") String url) {
		return GraphDatabase.driver(url);
	}

	@Bean
	public Neo4jStarwarsRepository starwarsRepository(Driver driver) {
		return new Neo4jStarwarsRepository(driver);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer pspc() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
