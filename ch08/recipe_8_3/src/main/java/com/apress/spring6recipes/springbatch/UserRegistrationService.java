package com.apress.spring6recipes.springbatch;

import java.time.LocalDate;

public interface UserRegistrationService {

	Iterable<UserRegistration> getOutstandingUserRegistrationBatchForDate(int quantity, LocalDate date);

	UserRegistration registerUser(UserRegistration userRegistrationRegistration);
}
