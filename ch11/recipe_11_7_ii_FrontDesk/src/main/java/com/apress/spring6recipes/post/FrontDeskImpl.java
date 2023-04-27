package com.apress.spring6recipes.post;

import org.springframework.kafka.core.KafkaOperations;

public class FrontDeskImpl implements FrontDesk {

	private final KafkaOperations<Integer, Object> kafkaOperations;

	public FrontDeskImpl(KafkaOperations<Integer, Object> kafkaOperations) {
		this.kafkaOperations = kafkaOperations;
	}

	public void sendMail(final Mail mail) {

		var result = kafkaOperations.send("mails", mail);
		result.whenComplete((sendResult, ex) -> {
			if (ex == null) {
				System.out.println("Result (success): " + sendResult.getRecordMetadata());
			} else {
				ex.printStackTrace();
			}
		});
	}
}
