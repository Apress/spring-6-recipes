package com.apress.spring6recipes.replicator;

import com.apress.spring6recipes.replicator.config.FileReplicatorConfig;
import com.apress.spring6recipes.replicator.config.QuartzConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		try (var context =
								 new AnnotationConfigApplicationContext(FileReplicatorConfig.class, QuartzConfiguration.class)) {
		}
	}
}