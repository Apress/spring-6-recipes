package com.apress.spring6recipes.post;

import org.springframework.jms.core.support.JmsGatewaySupport;

public class FrontDeskImpl extends JmsGatewaySupport implements FrontDesk {

	public void sendMail(final Mail mail) {
		getJmsTemplate().send(session -> {
			var message = session.createMapMessage();
			message.setString("mailId", mail.mailId());
			message.setString("country", mail.country());
			message.setDouble("weight", mail.weight());
			return message;
		});
	}
}
