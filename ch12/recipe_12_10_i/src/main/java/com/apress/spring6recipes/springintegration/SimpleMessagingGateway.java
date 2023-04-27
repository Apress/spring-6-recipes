package com.apress.spring6recipes.springintegration;

import org.springframework.integration.gateway.MessagingGatewaySupport;

public class SimpleMessagingGateway extends MessagingGatewaySupport {

    @SuppressWarnings("unchecked")
    public <T> T convertSendAndReceive(Object payload) {
        return (T) super.sendAndReceive(payload);
    }
}
