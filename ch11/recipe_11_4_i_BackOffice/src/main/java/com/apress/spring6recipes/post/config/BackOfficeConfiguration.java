package com.apress.spring6recipes.post.config;

import com.apress.spring6recipes.post.MailListener;
import jakarta.jms.ConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.SimpleMessageListenerContainer;

@Configuration
public class BackOfficeConfiguration {

    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory("tcp://localhost:61616");
    }

    @Bean
    public MailListener mailListener() {
        return new MailListener();
    }

    @Bean
    public Object container(ConnectionFactory cf, MailListener msgListener) {
        var smlc = new SimpleMessageListenerContainer();
        smlc.setConnectionFactory(cf);
        smlc.setDestinationName("mail.queue");
        smlc.setMessageListener(msgListener);
        return smlc;
    }
}
