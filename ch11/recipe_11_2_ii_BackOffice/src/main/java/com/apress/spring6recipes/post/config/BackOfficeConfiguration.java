package com.apress.spring6recipes.post.config;

import com.apress.spring6recipes.post.BackOfficeImpl;
import com.apress.spring6recipes.post.MailMessageConverter;
import jakarta.jms.ConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class BackOfficeConfiguration {

	@Bean
	public ConnectionFactory connectionFactory() {
		return new ActiveMQConnectionFactory("tcp://localhost:61616");
	}

	@Bean
	public JmsTemplate jmsTemplate(ConnectionFactory cf) {
		var jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(cf);
		jmsTemplate.setDefaultDestinationName("mail.queue");
		jmsTemplate.setReceiveTimeout(10000);
		jmsTemplate.setMessageConverter(new MailMessageConverter());
		return jmsTemplate;
	}

	@Bean
	public BackOfficeImpl backOffice(JmsTemplate jms) {
		var backOffice = new BackOfficeImpl();
		backOffice.setJmsTemplate(jms);
		return backOffice;
	}
}
