package com.apress.spring6recipes.springintegration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import java.util.Map;

public class Main {

	public static void main(String[] args) throws Exception {
		var cfg = IntegrationConfiguration.class;
		try (var ctx = new AnnotationConfigApplicationContext(cfg)) {

			var customerMap = Map.<String, Object>of(
							"id", 123L,
							"firstName", "Marten",
							"lastName", "Deinum");

			var jms = ctx.getBean(JmsTemplate.class);
			jms.convertAndSend("recipe-16-5", customerMap);

			System.out.println("Press [ENTER] to stop.");
			System.in.read();
		}
	}
}
