package com.apress.spring6recipes.reactive.court.domain;

public record Member(String name, String email,
										 String phone, SportType preferredType) {

	public Member(String name, String email, String phone) {
		this(name, email, phone, null);
	}
}