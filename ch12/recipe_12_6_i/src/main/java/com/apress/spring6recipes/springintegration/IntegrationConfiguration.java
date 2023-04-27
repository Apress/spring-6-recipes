package com.apress.spring6recipes.springintegration;

import jakarta.jms.ConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.jms.dsl.Jms;
import org.springframework.jms.connection.CachingConnectionFactory;

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
	public IntegrationFlow jmsInbound(ConnectionFactory connectionFactory) {
		return IntegrationFlow
						.from(Jms.messageDrivenChannelAdapter(connectionFactory)
										.extractPayload(true).destination("recipe-12-6")
										.errorChannel("errorChannel"))
						.transform(customerTransformer())
						.handle(customerServiceActivator())
						.get();
	}
}
