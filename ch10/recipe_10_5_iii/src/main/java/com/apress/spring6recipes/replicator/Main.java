package com.apress.spring6recipes.replicator;

import com.apress.spring6recipes.replicator.config.FileReplicatorConfig;
import com.apress.spring6recipes.replicator.config.QuartzConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		var cfg = "com.apress.spring6recipes.replicator.config";
		try (var ctx = new AnnotationConfigApplicationContext(cfg)) {}
	}
}