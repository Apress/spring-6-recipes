package com.apress.spring6recipes.replicator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;

@Configuration
public class JmxConfig {

	@Bean
	public MBeanExporter mbeanExporter() {
		return new AnnotationMBeanExporter();
	}
}

