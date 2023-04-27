package com.apress.spring6recipes.court.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ServerHttpObservationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.observation.DefaultMeterObservationHandler;
import io.micrometer.jmx.JmxConfig;
import io.micrometer.jmx.JmxMeterRegistry;
import io.micrometer.observation.ObservationRegistry;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.apress.spring6recipes.court")
public class CourtRestConfiguration {

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
	public ServerHttpObservationFilter metricsFilter(ObservationRegistry or) {
		return new ServerHttpObservationFilter(or);
	}
}

