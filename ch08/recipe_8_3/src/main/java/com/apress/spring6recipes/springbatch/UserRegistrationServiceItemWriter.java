package com.apress.spring6recipes.springbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class UserRegistrationServiceItemWriter implements ItemWriter<UserRegistration> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final UserRegistrationService urs;

	public UserRegistrationServiceItemWriter(UserRegistrationService urs) {
		this.urs = urs;
	}

	@Override
	public void write(Chunk<? extends UserRegistration> items) throws Exception {
		items.forEach(this::write);
	}

	private void write(UserRegistration ur) {
		var registration = urs.registerUser(ur);
		logger.debug("Registered: {}", registration);
	}
}
