package com.apress.spring6recipes.springintegration;

public record Customer(
    long id, String firstName, String lastName,
    String telephone, float creditScore) {

	public Customer(long id) {
		this(id, null, null, null, 0);
	}
}
