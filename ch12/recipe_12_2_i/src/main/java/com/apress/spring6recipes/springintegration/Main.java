package com.apress.spring6recipes.springintegration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import java.util.Map;


public class Main {

	public static void main(String[] args) {
		var cfg = IntegrationConfiguration.class;
		try (var applicationContext = new AnnotationConfigApplicationContext(cfg)) {
			var jmsTemplate = applicationContext.getBean(JmsTemplate.class);
			var customer = Map.<String, Object>of(
							"id", 1234L,
							"firstName", "Marten",
							"lastName", "Deinum");
			jmsTemplate.convertAndSend("recipe-12-2", customer);
		}
	}
}
