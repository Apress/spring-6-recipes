package com.apress.spring6recipes.post;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaOperations;

public class FrontDeskImpl implements FrontDesk {

	private final KafkaOperations<Integer, String> kafkaOperations;

	public FrontDeskImpl(KafkaOperations<Integer, String> kafkaOperations) {
		this.kafkaOperations = kafkaOperations;
	}

	public void sendMail(final Mail mail) {

		var result = kafkaOperations.send("mails", convertToJson(mail));
		result.whenComplete((sendResult, ex) -> {
			if (ex == null) {
				System.out.println("Result (success): " + sendResult.getRecordMetadata());
			} else {
				ex.printStackTrace();
			}
		});
	}

	private String convertToJson(Mail mail) {
		try {
			return new ObjectMapper().writeValueAsString(mail);
		} catch (JsonProcessingException ex) {
			throw new IllegalArgumentException(ex);
		}
	}
}
