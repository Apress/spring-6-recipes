package com.apress.spring6recipes.springintegration;

import java.util.Map;

import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;

public class InboundJMSMessageToCustomerTransformer {

    @Transformer
    public Customer transformJMSMapToCustomer(Message<Map<String, Object>> inboundSprignIntegrationMessage) {
        var jmsPayload = inboundSprignIntegrationMessage.getPayload();
        return convert(jmsPayload);
    }

	private Customer convert(Map<String, Object> payload) {
		return new Customer(
						(Long) payload.get("id"),
						(String) payload.get("firstName"),
						(String) payload.get("lastName"),
						(String) payload.getOrDefault("telephone", null),
						(Float) payload.getOrDefault("creditScore", 0f));
	}
}
