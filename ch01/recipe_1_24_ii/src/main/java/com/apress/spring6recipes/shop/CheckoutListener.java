package com.apress.spring6recipes.shop;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CheckoutListener {

	@EventListener
	public void onApplicationEvent(CheckoutEvent event) {
		// Do anything you like with the checkout time
		System.out.printf("Checkout event [%s]%n", event.getTime());
	}
}
