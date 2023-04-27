package com.apress.spring6recipes.post.config;

import com.apress.spring6recipes.post.FrontDeskImpl;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.Queue;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class FrontOfficeConfiguration {

	@Bean
	public ConnectionFactory connectionFactory() {
		return new ActiveMQConnectionFactory("tcp://localhost:61616");
	}

	@Bean
	public Queue mailDestination() {
		return new ActiveMQQueue("mail.queue");
	}

	@Bean
	public JmsTemplate jmsTemplate(ConnectionFactory cf, Queue destination) {
		var jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(cf);
		jmsTemplate.setDefaultDestination(destination);
		return jmsTemplate;
	}

	@Bean
	public FrontDeskImpl frontDesk(JmsTemplate jms) {
		return new FrontDeskImpl(jms);
	}
}
