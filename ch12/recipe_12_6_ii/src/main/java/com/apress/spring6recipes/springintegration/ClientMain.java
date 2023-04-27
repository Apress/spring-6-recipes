package com.apress.spring6recipes.springintegration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import java.util.Map;

public class ClientMain {
	public static void main(String[] args) {
		var cfg = ClientConfiguration.class;
		try (var ctx = new AnnotationConfigApplicationContext(cfg)) {

			var jmsTemplate = ctx.getBean(JmsTemplate.class);

			var customer = Map.<String, Object>of(
							"id", 1234L,
							"firstName", "Marten",
							"lastName", "Deinum");
			jmsTemplate.convertAndSend("recipe-16-6", customer);

			var customer2 = Map.<String, Object>of(
							"id", "1234L",
							"firstName", "Marten",
							"lastName", "Deinum");
			jmsTemplate.convertAndSend("recipe-16-6", customer2);
		}
	}
}
