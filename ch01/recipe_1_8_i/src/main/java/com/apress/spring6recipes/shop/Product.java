package com.apress.spring6recipes.shop;

import java.text.DecimalFormat;

public abstract class Product {

	private final String name;

	private final double price;

	private double discount;

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
		return String.format("%s: name=%s, price=$%.2f", getClass().getSimpleName(), name, price);
	}

}
