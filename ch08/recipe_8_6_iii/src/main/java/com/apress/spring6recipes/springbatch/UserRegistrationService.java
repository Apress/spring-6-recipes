package com.apress.spring6recipes.springbatch;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import java.time.LocalDate;

public interface UserRegistrationService {
	Iterable<UserRegistration> getOutstandingUserRegistrationBatchForDate(int quantity, LocalDate date);

	@Retryable(backoff = @Backoff(delay = 1000, maxDelay = 10000, multiplier = 2))
	UserRegistration registerUser(UserRegistration userRegistrationRegistration);
}
