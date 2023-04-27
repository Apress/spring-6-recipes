package com.apress.spring6recipes.springintegration;

import jakarta.jms.JMSException;
import jakarta.jms.MapMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

import java.util.Map;

public class InboundHelloWorldJMSMessageProcessor {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@ServiceActivator
	public void handleIncomingJmsMessageWithPayloadNotExtracted(
					Message<jakarta.jms.Message> msgWithJmsMessageAsPayload) throws Throwable {
		var payload = (MapMessage) msgWithJmsMessageAsPayload.getPayload();
		logger.debug("Received: {}", convert(payload));
		// you can imagine what we could do here: put
		// the record into the database, call a websrvice,
		// write it to a file, etc, etc
	}

	private Map<String, Object> convert(MapMessage msg) throws JMSException {
		return Map.of(
						"firstName", msg.getString("firstName"),
						"lastName", msg.getString("lastName"),
						"id", msg.getLong("id"));
	}
}
