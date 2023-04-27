package com.apress.spring6recipes.replicator;

import com.apress.spring6recipes.replicator.config.JmxServerConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Server {

	public static void main(String[] args) throws Exception {
		try (var ctx = new AnnotationConfigApplicationContext(JmxServerConfiguration.class)) {
			System.in.read();
		}
	}
}
