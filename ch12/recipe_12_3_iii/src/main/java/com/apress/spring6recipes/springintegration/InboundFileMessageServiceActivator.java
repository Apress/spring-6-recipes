package com.apress.spring6recipes.springintegration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;

import java.io.File;
import java.util.Map;

public class InboundFileMessageServiceActivator {

    private final Logger logger = LoggerFactory.getLogger(InboundFileMessageServiceActivator.class);

    @ServiceActivator
    public void interrogateMessage(
            @Headers Map<String, Object> headers, File file) {
			var msg = "the id of the message is {}, and name of the file payload is {}";
			var id = headers.get(MessageHeaders.ID);
			var filename = headers.get(FileHeaders.FILENAME);
      logger.debug(msg, id, filename);
    }
}
