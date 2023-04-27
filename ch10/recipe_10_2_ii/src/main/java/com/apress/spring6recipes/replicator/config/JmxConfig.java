package com.apress.spring6recipes.replicator.config;

import com.apress.spring6recipes.replicator.ReplicationNotificationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;

import javax.management.NotificationListener;
import java.util.Map;

@Configuration
public class JmxConfig {

	@Bean
	public ReplicationNotificationListener replicationNotificationListener() {
		return new ReplicationNotificationListener();
	}

	@Bean
	public AnnotationMBeanExporter mbeanExporter(NotificationListener nl) {
		var mbeanExporter = new AnnotationMBeanExporter();
		mbeanExporter.setDefaultDomain("bean");
		mbeanExporter.setNotificationListenerMappings(
				Map.of("bean:name=documentReplicator,type=JMXFileReplicator", nl));
		return mbeanExporter;
	}
}
