package com.apress.spring6recipes.shop;

public abstract class Product {

	private final String name;

	private final double price;

	public Product(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public String toString() {
		return name + " " + price;
	}

}
