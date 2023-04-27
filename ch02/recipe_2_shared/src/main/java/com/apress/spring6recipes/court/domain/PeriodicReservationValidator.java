package com.apress.spring6recipes.court.domain;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PeriodicReservationValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return PeriodicReservation.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		validateCourt(errors);
		validateTime(errors);
		validatePlayer(errors);
	}

	public void validateCourt(Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "courtName", "required.courtName", "Court name is required.");
	}

	public void validateTime(Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "fromDate", "required.fromDate", "From date is required.");
		ValidationUtils.rejectIfEmpty(errors, "toDate", "required.toDate", "To date is required.");
		ValidationUtils.rejectIfEmpty(errors, "period", "required.period", "Period is required.");
		ValidationUtils.rejectIfEmpty(errors, "hour", "required.hour", "Hour is required.");
	}

	public void validatePlayer(Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "player.name", "required.playerName",
				"Player name is required.");
	}
}
