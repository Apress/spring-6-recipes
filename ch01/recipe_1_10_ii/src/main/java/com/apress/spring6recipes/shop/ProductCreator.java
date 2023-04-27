package com.apress.spring6recipes.shop;

import java.util.Map;

public class ProductCreator {

	private final Map<String, Product> products;

	public ProductCreator(Map<String, Product> products) {
		this.products = products;
	}

	public Product createProduct(String productId) {
		Product product = products.get(productId);
		if (product != null) {
			return product;
		}
		var msg = "Unknown product '" + productId + "'";
		throw new IllegalArgumentException(msg);
	}
}
