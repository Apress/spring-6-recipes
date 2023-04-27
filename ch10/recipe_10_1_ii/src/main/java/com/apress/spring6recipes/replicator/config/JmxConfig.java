package com.apress.spring6recipes.replicator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.MBeanExporter;

import java.util.Map;

@Configuration
public class JmxConfig {

	@Bean
	public MBeanExporter mbeanExporter() {
		var beansToExport = Map.<String, Object>of(
						"bean:name=documentReplicator", "documentReplicator");
		var mbeanExporter = new MBeanExporter();
		mbeanExporter.setBeans(beansToExport);
		return mbeanExporter;
	}
}
