package com.apress.spring6recipes.springintegration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.integration.gateway.GatewayProxyFactoryBean;
import org.springframework.messaging.MessageChannel;

public class Main {

	public static void main(String[] args) {
		var cfg = AdditionConfiguration.class;
	 	try (var ctx = new AnnotationConfigApplicationContext(cfg)) {
			var request = ctx.getBean("request", MessageChannel.class);
			var response = ctx.getBean("response", MessageChannel.class);

		  var gateway = new GatewayProxyFactoryBean();
			gateway.setDefaultRequestChannel(request);
		  gateway.setDefaultReplyChannel(response);
			gateway.setBeanFactory(ctx);
			gateway.afterPropertiesSet();;
			gateway.start();

			var msgGateway = new SimpleMessagingGateway();
			msgGateway.setRequestChannel(request);
			msgGateway.setReplyChannel(response);
			msgGateway.setBeanFactory(ctx);
			msgGateway.afterPropertiesSet();
			msgGateway.start();

			Number result = msgGateway.convertSendAndReceive(new Operands(22, 4));
			System.out.printf("Result: %f%n", result.floatValue());
		}
	}
}
