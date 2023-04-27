package com.apress.spring6recipes.shop;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import java.time.LocalDateTime;

public class Cashier implements ApplicationEventPublisherAware {

	private ApplicationEventPublisher applicationEventPublisher;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher aep) {
		this.applicationEventPublisher = aep;
	}

	public void checkout(ShoppingCart cart) {
		var event = new CheckoutEvent(cart, LocalDateTime.now());
		applicationEventPublisher.publishEvent(event);
	}
}
