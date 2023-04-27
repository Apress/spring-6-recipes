package com.apress.spring6recipes.court.domain;

import jakarta.validation.constraints.NotEmpty;

public class Player {

	@NotEmpty
	private String name;

	@NotEmpty
	private String phone;

	public Player() {
	}

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
