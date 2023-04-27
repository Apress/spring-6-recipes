package com.apress.spring6recipes.springintegration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

import java.util.Map;

public class InboundHelloWorldJMSMessageProcessor {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@ServiceActivator
	public void handleIncomingJmsMessage(
					Message<Map<String, Object>> inboundJmsMessage) {
		var payload = inboundJmsMessage.getPayload();
		logger.info("Received: {}", payload);
		// you can imagine what we could do here: put
		// the record into the database, call a websrvice,
		// write it to a file, etc, etc
	}
}
