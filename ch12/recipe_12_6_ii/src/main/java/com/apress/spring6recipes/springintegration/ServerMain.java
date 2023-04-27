package com.apress.spring6recipes.springintegration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class ServerMain {
	public static void main(String[] args) throws Exception {
		var cfg = IntegrationConfiguration.class;
		try (var context = new AnnotationConfigApplicationContext(cfg)) {
			System.out.println("Press [ENTER] to stop.");
			System.in.read();
		}
	}
}
