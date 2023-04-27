package com.apress.spring6recipes.post.config;

import com.apress.spring6recipes.post.MailListener;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class BackOfficeConfiguration {

	@Bean
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory cf) {
		var containerFactory = new SimpleRabbitListenerContainerFactory();
		containerFactory.setConnectionFactory(cf);
		containerFactory.setMessageConverter(new Jackson2JsonMessageConverter());
		return containerFactory;
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		var connectionFactory = new CachingConnectionFactory("127.0.0.1");
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		connectionFactory.setPort(5672);
		return connectionFactory;
	}

	@Bean
	public MailListener mailListener() {
		return new MailListener();
	}
}
