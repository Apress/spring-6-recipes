package com.apress.spring6recipes.post;

import jakarta.jms.JMSException;
import jakarta.jms.MapMessage;

import jakarta.jms.Message;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.JmsUtils;

public class BackOfficeImpl implements BackOffice {

    private final JmsTemplate jmsTemplate;

	public BackOfficeImpl(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public Mail receiveMail() {
		var message = jmsTemplate.receive();
		try {
			return message != null ? convert(message) : null;
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
}
