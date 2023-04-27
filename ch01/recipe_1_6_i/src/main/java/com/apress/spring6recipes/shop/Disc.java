package com.apress.spring6recipes.shop;

public class Disc extends Product {

	private final int capacity;

	public Disc(String name, double price, int capacity, double discount) {
		super(name, price, discount);
		this.capacity = capacity;
	}

	public int getCapacity() {
		return capacity;
	}

}
