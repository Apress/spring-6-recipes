package com.apress.spring6recipes.springintegration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.core.JmsTemplate;

public class Main {

	public static void main(String[] args) throws Exception {
		var cfg = IntegrationConfiguration.class;
		try (var context = new AnnotationConfigApplicationContext(cfg)) {

			var customerMap = Map.of(
							"id", 123L,
							"firstName", "Marten",
							"lastName", "Deinum");

			var jms = context.getBean(JmsTemplate.class);
			jms.convertAndSend("recipe-12-5", customerMap);

			System.out.println("Press [ENTER] to stop.");
			System.in.read();
		}
	}
}
