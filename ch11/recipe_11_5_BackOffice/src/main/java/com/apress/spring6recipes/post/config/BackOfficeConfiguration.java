package com.apress.spring6recipes.post.config;

import com.apress.spring6recipes.post.MailListener;
import com.apress.spring6recipes.post.MailMessageConverter;
import jakarta.jms.ConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.messaginghub.pooled.jms.JmsPoolConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
@EnableJms
public class BackOfficeConfiguration {

    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory("tcp://localhost:61616");
    }

		@Bean
		@Primary
		public JmsPoolConnectionFactory pooledConnectionFactory(ConnectionFactory cf) {
			var pooledCf = new JmsPoolConnectionFactory();
			pooledCf.setConnectionFactory(cf);
			return pooledCf;
		}

    @Bean
    public MailListener mailListener() {
        return new MailListener();
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory cf) {
        var listenerContainerFactory = new DefaultJmsListenerContainerFactory();
        listenerContainerFactory.setConnectionFactory(cf);
        listenerContainerFactory.setMessageConverter(new MailMessageConverter());
        listenerContainerFactory.setSessionTransacted(true);
        return listenerContainerFactory;
    }
}
