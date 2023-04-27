package com.apress.spring6recipes.vehicle.config;

import static io.r2dbc.spi.ConnectionFactoryOptions.DATABASE;
import static io.r2dbc.spi.ConnectionFactoryOptions.DRIVER;
import static io.r2dbc.spi.ConnectionFactoryOptions.HOST;
import static io.r2dbc.spi.ConnectionFactoryOptions.PASSWORD;
import static io.r2dbc.spi.ConnectionFactoryOptions.PORT;
import static io.r2dbc.spi.ConnectionFactoryOptions.USER;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;

@Configuration
@EnableR2dbcRepositories("com.apress.spring6recipes.vehicle")
public class VehicleConfiguration extends AbstractR2dbcConfiguration {

	@Bean
	public ConnectionFactory connectionFactory() {
		var options = ConnectionFactoryOptions.builder()
						.option(DRIVER, "postgresql")
						.option(HOST, "localhost").option(PORT, 5432)
						.option(DATABASE, "vehicle")
						.option(USER, "postgres").option(PASSWORD, "password")
						.build();
		return ConnectionFactories.get(options);
	}

	@Bean
	public ConnectionFactoryInitializer initializer(ConnectionFactory cf) {
		var initializer = new ConnectionFactoryInitializer();
		initializer.setConnectionFactory(cf);
		initializer.setDatabasePopulator(new ResourceDatabasePopulator(
						new ClassPathResource("/sql/vehicle.sql")));
		return initializer;
	}
}
