package com.apress.spring6recipes.springintegration;

import jakarta.jms.ConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.jms.dsl.Jms;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableIntegration
public class IntegrationConfiguration {

    @Bean
    public CachingConnectionFactory connectionFactory() {
        var connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        return new CachingConnectionFactory(connectionFactory);
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        return new JmsTemplate(connectionFactory);
    }

    @Bean
    public InboundHelloWorldJMSMessageProcessor messageProcessor() {
        return new InboundHelloWorldJMSMessageProcessor();
    }

    @Bean
    public IntegrationFlow jmsInbound(ConnectionFactory connectionFactory,
				    InboundHelloWorldJMSMessageProcessor messageProcessor) {
        return IntegrationFlow
                .from(Jms.messageDrivenChannelAdapter(connectionFactory).extractPayload(true).destination("recipe-12-2"))
                .handle(messageProcessor)
                .get();
    }
}
