package com.apress.spring6recipes.shop;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Scope("prototype")
@Lazy
public class ShoppingCart {

	private final List<Product> items = new ArrayList<>();

	public void addItem(Product item) {
		this.items.add(item);
	}

	public List<Product> getItems() {
		return Collections.unmodifiableList(this.items);
	}
}
