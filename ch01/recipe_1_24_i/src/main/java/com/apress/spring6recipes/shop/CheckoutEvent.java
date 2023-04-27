package com.apress.spring6recipes.shop;

import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class CheckoutEvent extends ApplicationEvent {

	private final ShoppingCart cart;
	private final LocalDateTime time;

	public CheckoutEvent(ShoppingCart cart, LocalDateTime time) {
		super(cart);
		this.cart = cart;
		this.time = time;
	}

	public ShoppingCart getCart() {
		return cart;
	}

	public LocalDateTime getTime() {
		return this.time;
	}
}
