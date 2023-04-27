package com.apress.spring6recipes.court.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

@Configuration
public class AsyncConfiguration implements WebMvcConfigurer {

	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		configurer.setDefaultTimeout(Duration.ofSeconds(5).toMillis());
		configurer.setTaskExecutor(mvcTaskExecutor());
	}

	@Bean
	public AsyncTaskExecutor mvcTaskExecutor() {
		var taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setThreadGroupName("mvcTaskExecutor");
		return taskExecutor;
	}
}
