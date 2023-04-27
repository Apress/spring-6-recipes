package com.apress.spring6recipes.replicator;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.metrics.jfr.FlightRecorderApplicationStartup;

public class Main {

	public static void main(String[] args) throws Exception {
		var cfg = "com.apress.spring6recipes.replicator.config";
		try (var ctx = new AnnotationConfigApplicationContext()) {
			ctx.setApplicationStartup(new FlightRecorderApplicationStartup());
			ctx.scan(cfg);
			ctx.refresh();
			System.in.read();
		}
	}
}
