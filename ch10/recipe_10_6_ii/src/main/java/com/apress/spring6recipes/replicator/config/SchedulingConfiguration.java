package com.apress.spring6recipes.replicator.config;

import com.apress.spring6recipes.replicator.FileReplicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.io.IOException;
import java.time.Duration;

@Configuration
@EnableScheduling
public class SchedulingConfiguration implements SchedulingConfigurer {

	private final FileReplicator fileReplicator;

	public SchedulingConfiguration(FileReplicator fileReplicator) {
		this.fileReplicator = fileReplicator;
	}

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.addFixedDelayTask(() -> {
			try {
				fileReplicator.replicate();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}, Duration.ofSeconds(60));
	}

	@Bean
	public TaskScheduler taskScheduler() {
		var taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setThreadNamePrefix("s6r-scheduler-");
		taskScheduler.setPoolSize(10);
		return taskScheduler;
	}
}
