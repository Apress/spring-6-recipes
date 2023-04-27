package com.apress.spring6recipes.post;

import org.springframework.jms.core.support.JmsGatewaySupport;

public class BackOfficeImpl extends JmsGatewaySupport implements BackOffice {

	public Mail receiveMail() {
		return (Mail) getJmsTemplate().receiveAndConvert();
	}
}
