package com.apress.spring6recipes.court.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Member {

	@NotBlank
	private String name;
	@NotBlank
	@Email
	private String email;
	private String phone;

	public Member() {}

	public Member(String name, String phone, String email) {
		this.name=name;
		this.phone=phone;
		this.email=email;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
