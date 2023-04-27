package com.apress.spring6recipes.post;

import jakarta.jms.JMSException;
import org.springframework.jms.core.support.JmsGatewaySupport;
import org.springframework.jms.support.JmsUtils;

import java.util.Map;

public class BackOfficeImpl extends JmsGatewaySupport implements BackOffice {

	public Mail receiveMail() {
		var message = (Map<String, ?>) getJmsTemplate().receiveAndConvert();
		try {
			return message != null ? convert(message) : null;
		} catch (JMSException e) {
			throw JmsUtils.convertJmsAccessException(e);
		}
	}

	private Mail convert(Map<String, ?> msg) throws JMSException {
		return new Mail(
						(String) msg.get("mailId"),
						(String) msg.get("country"),
						(Double) msg.get("weight"));
	}
}
