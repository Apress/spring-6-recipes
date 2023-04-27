package com.apress.spring6recipes.replicator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableScheduling
public class SchedulingConfiguration {

	@Bean
	public TaskScheduler taskScheduler() {
		var taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setThreadNamePrefix("s6r-scheduler-");
		taskScheduler.setPoolSize(10);
		return taskScheduler;
	}
}