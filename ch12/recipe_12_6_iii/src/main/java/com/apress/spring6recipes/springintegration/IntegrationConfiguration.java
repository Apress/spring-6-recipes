package com.apress.spring6recipes.springintegration;

import java.util.Map;

import jakarta.jms.ConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.jms.dsl.Jms;
import org.springframework.integration.router.ErrorMessageExceptionTypeRouter;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.messaging.MessageHandlingException;

@Configuration
@EnableIntegration
public class IntegrationConfiguration {

	@Bean
	public CachingConnectionFactory connectionFactory() {
		var connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		return new CachingConnectionFactory(connectionFactory);
	}

	@Bean
	public InboundJMSMessageToCustomerTransformer customerTransformer() {
		return new InboundJMSMessageToCustomerTransformer();
	}

	@Bean
	public InboundCustomerServiceActivator customerServiceActivator() {
		return new InboundCustomerServiceActivator();
	}

	@Bean
	public DefaultErrorHandlingServiceActivator errorHandlingServiceActivator() {
		return new DefaultErrorHandlingServiceActivator();
	}

	@Bean
	public ErrorMessageExceptionTypeRouter exceptionTypeRouter() {
		var mappings = Map.of(
				MyCustomException.class.getName(), "customExceptionChannel",
				RuntimeException.class.getName(), "runtimeExceptionChannel",
				MessageHandlingException.class.getName(), "messageHandlingExceptionChannel"
		);
		var router = new ErrorMessageExceptionTypeRouter();
		router.setChannelMappings(mappings);
		return router;
	}

	@Bean
	public IntegrationFlow errorFlow() {
		return IntegrationFlow
						.from("errorChannel")
						.route(exceptionTypeRouter())
						.get();
	}

	@Bean
	public IntegrationFlow customExceptionFlow() {
		return IntegrationFlow
						.from("customExceptionChannel")
						.handle(errorHandlingServiceActivator())
						.get();
	}

	@Bean
	public IntegrationFlow jmsInbound(ConnectionFactory connectionFactory) {
		return IntegrationFlow
						.from(Jms.messageDrivenChannelAdapter(connectionFactory)
										.extractPayload(true).destination("recipe-12-6")
										.errorChannel("errorChannel").extractPayload(true))
						.transform(new InboundJMSMessageToCustomerTransformer())
						.handle(customerServiceActivator())
						.get();
	}
}
