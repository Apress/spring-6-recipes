package com.apress.spring6recipes.springintegration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;

public class DefaultErrorHandlingServiceActivator {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@ServiceActivator
	public void handleThrowable(Message<Throwable> errorMessage) {
		var throwable = errorMessage.getPayload();
		logger.error("Message: {}", throwable.getMessage(), throwable);

		if (throwable instanceof MessagingException me) {
			Message<?> failedMessage = me.getFailedMessage();

			if (failedMessage != null) {
				// do something with the original message
			}
		} else {
			// it's something that was thrown in the execution of code in some component you created
		}
	}
}
