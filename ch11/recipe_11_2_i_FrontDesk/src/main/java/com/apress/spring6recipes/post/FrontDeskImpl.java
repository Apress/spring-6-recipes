package com.apress.spring6recipes.post;

import org.springframework.jms.core.support.JmsGatewaySupport;

import java.util.Map;

public class FrontDeskImpl extends JmsGatewaySupport implements FrontDesk {

	public void sendMail(final Mail mail) {
		var map = Map.of(
						"mailId", mail.mailId(),
						"country", mail.country(),
						"weight", mail.weight());
		getJmsTemplate().convertAndSend(map);
	}
}
