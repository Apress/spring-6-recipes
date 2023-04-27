package com.apress.spring6recipes.court;

import java.time.Duration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.RestTemplate;

import com.apress.spring6recipes.court.domain.Members;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.observation.DefaultMeterObservationHandler;
import io.micrometer.jmx.JmxConfig;
import io.micrometer.jmx.JmxMeterRegistry;
import io.micrometer.observation.ObservationRegistry;

public class MemberClient {

	public static void main(String[] args) throws Exception {
		var cfg = MemberClientConfiguration.class;
		try (var ctx = new AnnotationConfigApplicationContext(cfg)) {
			var scheduler = ctx.getBean(TaskScheduler.class);
			var restTemplate = ctx.getBean(RestTemplate.class);
			scheduler.scheduleAtFixedRate( () -> listMembers(restTemplate), Duration.ofMillis(100));
			System.in.read();
		}
	}

	private static void listMembers(RestTemplate rest) {
		var uri = "http://127.0.0.1:8080/court/members";
		var result = rest.getForObject(uri, Members.class);
		System.out.println(result);
	}

	@Configuration
	static class MemberClientConfiguration {

		@Bean
		public ThreadPoolTaskScheduler taskExecutor() {
			var taskScheduler = new ThreadPoolTaskScheduler();
			taskScheduler.setThreadGroupName("memberClientExecutor");
			taskScheduler.setPoolSize(2);
			return taskScheduler;
		}

		@Bean
		public JmxMeterRegistry meterRegistry() {
			return new JmxMeterRegistry(JmxConfig.DEFAULT, Clock.SYSTEM);
		}

		@Bean
		public ObservationRegistry observationRegistry(MeterRegistry meterRegistry) {
			var registry = ObservationRegistry.create();
			var handler = new DefaultMeterObservationHandler(meterRegistry);
			registry.observationConfig().observationHandler(handler);
			return registry;
		}

		@Bean
		public RestTemplate restTemplate(ObservationRegistry observationRegistry) {
			var restTemplate = new RestTemplate();
			restTemplate.setObservationRegistry(observationRegistry);
			return restTemplate;
		}
	}
}
