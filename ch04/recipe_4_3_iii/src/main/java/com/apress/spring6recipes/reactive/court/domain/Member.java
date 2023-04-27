package com.apress.spring6recipes.reactive.court.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record Member(@NotBlank String name,
										 @NotBlank @Email String email,
										 String phone) { }
