package com.apress.spring6recipes.post;

import jakarta.jms.Destination;
import org.springframework.jms.core.JmsTemplate;

public class FrontDeskImpl implements FrontDesk {

	private final JmsTemplate jmsTemplate;
	private final Destination destination;

	public FrontDeskImpl(JmsTemplate jmsTemplate, Destination destination) {
		this.jmsTemplate = jmsTemplate;
		this.destination = destination;
	}

	public void sendMail(Mail mail) {
		jmsTemplate.send(destination, session -> {
			var message = session.createMapMessage();
			message.setString("mailId", mail.mailId());
			message.setString("country", mail.country());
			message.setDouble("weight", mail.weight());
			return message;
		});
	}
}
