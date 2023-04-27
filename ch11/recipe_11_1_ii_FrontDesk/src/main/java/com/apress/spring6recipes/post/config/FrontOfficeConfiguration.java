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
	public JmsTemplate jmsTemplate(ConnectionFactory cf) {
		return new JmsTemplate(cf);
	}

	@Bean
	public FrontDeskImpl frontDesk(JmsTemplate jms, Queue destination) {
		return new FrontDeskImpl(jms, destination);
	}
}
