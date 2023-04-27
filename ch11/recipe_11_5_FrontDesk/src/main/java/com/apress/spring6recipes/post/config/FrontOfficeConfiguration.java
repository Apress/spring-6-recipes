package com.apress.spring6recipes.post.config;

import com.apress.spring6recipes.post.FrontDeskImpl;
import com.apress.spring6recipes.post.MailMessageConverter;
import jakarta.jms.ConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class FrontOfficeConfiguration {

	@Bean
	public ConnectionFactory connectionFactory() {
		return new ActiveMQConnectionFactory("tcp://localhost:61616");
	}

	@Bean
	public CachingConnectionFactory cachingConnectionFactory(ConnectionFactory cf) {
		return new CachingConnectionFactory(cf);
	}

	@Bean
	public JmsTemplate jmsTemplate(CachingConnectionFactory cf) {
		var jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(cf);
		jmsTemplate.setDefaultDestinationName("mail.queue");
		jmsTemplate.setMessageConverter(new MailMessageConverter());
		return jmsTemplate;
	}

	@Bean
	public JmsTransactionManager transactionManager(ConnectionFactory connectionFactory) {
		return new JmsTransactionManager(connectionFactory);
	}

	@Bean
	public FrontDeskImpl frontDesk(JmsTemplate jms) {
		var frontDesk = new FrontDeskImpl();
		frontDesk.setJmsTemplate(jms);
		return  frontDesk;
	}
}
