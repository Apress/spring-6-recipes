package com.apress.spring6recipes.springbatch;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.support.RetryTemplate;

public class RetryableUserRegistrationServiceItemWriter
				implements ItemWriter<UserRegistration> {

	private final UserRegistrationService userRegistrationService;

	private final RetryTemplate retryTemplate;

	public RetryableUserRegistrationServiceItemWriter(UserRegistrationService usr,
																										RetryTemplate retryTemplate) {
		this.userRegistrationService = usr;
		this.retryTemplate = retryTemplate;
	}

	public void write(Chunk<? extends UserRegistration> items)
					throws Exception {
		for (var userRegistration : items) {
			retryTemplate.execute(context ->
							userRegistrationService.registerUser(userRegistration));
		}
	}
}
