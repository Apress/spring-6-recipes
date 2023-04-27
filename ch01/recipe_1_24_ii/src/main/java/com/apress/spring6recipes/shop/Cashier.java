package com.apress.spring6recipes.shop;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Cashier {

	private final ApplicationEventPublisher applicationEventPublisher;

	public Cashier(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	public void checkout(ShoppingCart cart) {
		var event = new CheckoutEvent(cart, LocalDateTime.now());
		applicationEventPublisher.publishEvent(event);
	}
}
