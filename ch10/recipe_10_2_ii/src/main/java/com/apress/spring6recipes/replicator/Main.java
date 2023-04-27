package com.apress.spring6recipes.replicator;

import com.apress.spring6recipes.replicator.config.JmxConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		try (var ctx = new AnnotationConfigApplicationContext(FileReplicator.class, JmxConfig.class)) {
			System.in.read();
		}
	}
}
