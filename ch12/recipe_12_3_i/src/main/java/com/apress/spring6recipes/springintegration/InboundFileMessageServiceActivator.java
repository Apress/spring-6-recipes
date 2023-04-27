package com.apress.spring6recipes.springintegration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

import java.io.File;

public class InboundFileMessageServiceActivator {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ServiceActivator
    public void interrogateMessage(Message<File> message) {
        var headers = message.getHeaders();
				headers.forEach( (k,v) -> logger.debug("{} : {}", k, v));
    }
}
