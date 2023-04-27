package com.apress.spring6recipes.shop;

public abstract class Product {

	private final String name;
	private double price;

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

	void setPrice(double price) {
		this.price=price;
	}

	public String toString() {
		return String.format("%s: name=%s, price=$%.2f", getClass().getSimpleName(), name, price);
	}

}
