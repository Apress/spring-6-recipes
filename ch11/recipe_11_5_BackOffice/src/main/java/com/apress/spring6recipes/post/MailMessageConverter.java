package com.apress.spring6recipes.post;

import jakarta.jms.JMSException;
import jakarta.jms.MapMessage;
import jakarta.jms.Message;
import jakarta.jms.Session;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

public class MailMessageConverter implements MessageConverter {

	public Object fromMessage(Message message)
					throws JMSException, MessageConversionException {
		var mapMessage = (MapMessage) message;
		return new Mail(
						mapMessage.getString("mailId"),
						mapMessage.getString("country"),
						mapMessage.getDouble("weight"));
	}

	public Message toMessage(Object object, Session session)
					throws JMSException, MessageConversionException {
		var mail = (Mail) object;
		var message = session.createMapMessage();
		message.setString("mailId", mail.mailId());
		message.setString("country", mail.country());
		message.setDouble("weight", mail.weight());
		return message;
	}
}
