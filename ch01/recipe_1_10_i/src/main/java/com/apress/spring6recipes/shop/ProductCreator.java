package com.apress.spring6recipes.shop;

public class ProductCreator {

	public static Product createProduct(String productId) {
		return switch (productId) {
			case "aaa" -> new Battery("AAA", 2.5, true);
			case "cdrw" -> new Disc("CD-RW", 1.5, 700);
			case "dvdrw" -> new Disc("DVD-RW", 3.0, 4700);
			default -> {
				var msg = "Unknown product '" + productId + "'";
				throw new IllegalArgumentException(msg);
			}
		};
	}
}
