package com.apress.spring6recipes.replicator.config;

import com.apress.spring6recipes.replicator.FileReplicator;

import org.springframework.context.ApplicationStartupAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.metrics.ApplicationStartup;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.io.IOException;
import java.time.Duration;

@Configuration
@EnableScheduling
public class SchedulingConfiguration implements SchedulingConfigurer, ApplicationStartupAware {

	private final FileReplicator fileReplicator;
	private ApplicationStartup applicationStartup;

	public SchedulingConfiguration(FileReplicator fileReplicator) {
		this.fileReplicator = fileReplicator;
	}

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		var step = applicationStartup.start("s6r.register-task");
		taskRegistrar.addFixedDelayTask(() -> {
			try {
				fileReplicator.replicate();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}, Duration.ofSeconds(60));
		step.end();
	}

	@Bean
	public TaskScheduler taskScheduler() {
		var taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setThreadNamePrefix("s6r-scheduler-");
		taskScheduler.setPoolSize(10);
		return taskScheduler;
	}

	@Override
	public void setApplicationStartup(ApplicationStartup applicationStartup) {
		this.applicationStartup=applicationStartup;
	}
}
