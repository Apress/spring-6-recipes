package com.apress.spring6recipes.springintegration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;

import java.io.File;

public class InboundFileMessageServiceActivator {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ServiceActivator
    public void interrogateMessage(
            @Header(MessageHeaders.ID) String uuid,
            @Header(FileHeaders.FILENAME) String fileName, File file) {
			var msg = "the id of the message is {}, and name of the file payload is {}";
      logger.debug(msg, uuid, fileName);
    }
}
