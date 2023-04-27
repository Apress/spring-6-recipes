package com.apress.spring6recipes.post;

import jakarta.jms.Session;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;

import java.util.Map;

public class FrontDeskImpl implements FrontDesk {

	public void sendMail(Mail mail) {
		try (var cf = new ActiveMQConnectionFactory("tcp://localhost:61616");
				 var ctx = cf.createContext(Session.AUTO_ACKNOWLEDGE)) {
			var destination = new ActiveMQQueue("mail.queue");
			var mapContext = Map.<String, Object>of(
							"mailId", mail.mailId(),
							"country", mail.country(),
							"weight", mail.weight());
			ctx.createProducer().send(destination, mapContext);
		}
	}
}

