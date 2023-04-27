package com.apress.spring6recipes.springbatch.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@ComponentScan("com.apress.spring6recipes.springbatch")
@PropertySource("classpath:/batch.properties")
@EnableScheduling
@EnableAsync
public class BatchConfiguration {

	@Bean
	public DataSource dataSource(Environment env) {
		var dataSource = new DriverManagerDataSource();
		dataSource.setUrl(env.getRequiredProperty("datasource.url"));
		dataSource.setUsername(env.getProperty("datasource.username"));
		dataSource.setPassword(env.getProperty("datasource.password"));
		return dataSource;
	}

	@Bean
	public DataSourceTransactionManager transactionManager(DataSource ds) {
		return new DataSourceTransactionManager(ds);
	}

	@Bean
	public DataSourceInitializer databasePopulator(DataSource dataSource) {
		var populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("org/springframework/batch/core/schema-h2.sql"));
		populator.addScript(new ClassPathResource("sql/reset_user_registration.sql"));
		populator.setContinueOnError(true);
		populator.setIgnoreFailedDrops(true);

		var initializer = new DataSourceInitializer();
		initializer.setDatabasePopulator(populator);
		initializer.setDataSource(dataSource);
		return initializer;
	}

	@Bean
	public ThreadPoolTaskExecutor taskExecutor() {
		var taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setThreadGroupName("batch-executor");
		taskExecutor.setMaxPoolSize(10);
		return taskExecutor;
	}

	@Bean
	public ThreadPoolTaskScheduler taskScheduler() {
		var taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setThreadGroupName("batch-scheduler");
		taskScheduler.setPoolSize(10);
		return taskScheduler;
	}

}
