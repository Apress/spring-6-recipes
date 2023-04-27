package com.apress.spring6recipes.springintegration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		var cfg = IntegrationConfiguration.class;
		try (var ctx = new AnnotationConfigApplicationContext(cfg)) {}
	}
}
