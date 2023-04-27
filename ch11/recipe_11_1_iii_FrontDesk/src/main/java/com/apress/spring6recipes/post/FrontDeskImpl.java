package com.apress.spring6recipes.post;

import org.springframework.jms.core.JmsTemplate;

public class FrontDeskImpl implements FrontDesk {

	private final JmsTemplate jmsTemplate;

	public FrontDeskImpl(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void sendMail(final Mail mail) {
		jmsTemplate.send(session -> {
			var message = session.createMapMessage();
			message.setString("mailId", mail.mailId());
			message.setString("country", mail.country());
			message.setDouble("weight", mail.weight());
			return message;
		});
	}
}
