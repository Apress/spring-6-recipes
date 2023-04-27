package com.apress.spring6recipes.post;

import jakarta.jms.JMSException;
import jakarta.jms.MapMessage;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import org.springframework.jms.support.JmsUtils;

public class MailListener implements MessageListener {

	public void onMessage(Message message) {
		try {
			var mail = convert(message);
			displayMail(mail);
		} catch (JMSException e) {
			throw JmsUtils.convertJmsAccessException(e);
		}
	}

	private Mail convert(Message msg) throws JMSException {
		var message = (MapMessage) msg;
		return new Mail(message.getString("mailId"),
						message.getString("country"),
						message.getDouble("weight"));
	}

	private void displayMail(Mail mail) {
		System.out.printf("Received: %s%n", mail);
	}
}
