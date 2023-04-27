package com.apress.spring6recipes.reactive.court.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record Member(@NotBlank String name,
										 @NotBlank @Email String email,
										 String phone,
										 @NotNull SportType preferredType) {

	public Member(String name,
								String email, String phone) {
		this(name, email, phone, null);
	}
}