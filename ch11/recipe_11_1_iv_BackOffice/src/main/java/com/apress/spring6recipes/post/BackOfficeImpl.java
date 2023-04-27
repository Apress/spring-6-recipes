package com.apress.spring6recipes.post;

import jakarta.jms.JMSException;
import jakarta.jms.MapMessage;
import jakarta.jms.Message;
import org.springframework.jms.core.support.JmsGatewaySupport;
import org.springframework.jms.support.JmsUtils;

public class BackOfficeImpl extends JmsGatewaySupport implements BackOffice {

	public Mail receiveMail() {
		var message = getJmsTemplate().receive();
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
