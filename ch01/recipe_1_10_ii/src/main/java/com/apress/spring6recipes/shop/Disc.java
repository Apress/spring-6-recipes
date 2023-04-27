package com.apress.spring6recipes.shop;

public class Disc extends Product {

	private final int capacity;

	public Disc(String name, double price, int capacity) {
		super(name, price);
		this.capacity = capacity;
	}

	public int getCapacity() {
		return capacity;
	}

	@Override
	public String toString() {
		String msg = super.toString() + ", capacity=%dMB";
		return String.format(msg, this.capacity);
	}

}
