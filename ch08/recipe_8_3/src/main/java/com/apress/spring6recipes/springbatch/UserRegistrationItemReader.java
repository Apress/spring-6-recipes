package com.apress.spring6recipes.springbatch;

import org.springframework.batch.item.ItemReader;

import java.time.LocalDate;

public class UserRegistrationItemReader implements ItemReader<UserRegistration> {

	private final UserRegistrationService usr;

	public UserRegistrationItemReader(UserRegistrationService usr) {
		this.usr = usr;
	}

	public UserRegistration read() throws Exception {
		var today = LocalDate.now();
		var registrations = usr.getOutstandingUserRegistrationBatchForDate(1, today);
		var iter = registrations.iterator();
		return iter.hasNext() ? iter.next() : null;
	}
}
