package com.apress.spring6recipes.shop;

import java.time.LocalDateTime;

public class CheckoutEvent {

	private final ShoppingCart cart;
	private final LocalDateTime time;

	public CheckoutEvent(ShoppingCart cart, LocalDateTime time) {
		this.cart = cart;
		this.time = time;
	}

	public ShoppingCart getCart() {
		return this.cart;
	}

	public LocalDateTime getTime() {
		return time;
	}
}
