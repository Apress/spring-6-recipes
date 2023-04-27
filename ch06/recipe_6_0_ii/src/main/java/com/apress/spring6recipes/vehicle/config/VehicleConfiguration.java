package com.apress.spring6recipes.vehicle.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.spring6recipes.vehicle.PlainJdbcVehicleDao;
import com.apress.spring6recipes.vehicle.VehicleDao;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class VehicleConfiguration {

	@Bean
	public VehicleDao vehicleDao(DataSource dataSource) {
		return new PlainJdbcVehicleDao(dataSource);
	}

	@Bean
	public DataSource dataSource() {
		var dataSource = new HikariDataSource();
		dataSource.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
		dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/vehicle");
		dataSource.setUsername("postgres");
		dataSource.setPassword("password");
		dataSource.setMinimumIdle(2);
		dataSource.setMaximumPoolSize(5);
		return dataSource;
	}
}
