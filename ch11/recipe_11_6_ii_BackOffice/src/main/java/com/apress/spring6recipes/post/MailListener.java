package com.apress.spring6recipes.post;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class MailListener {

	@RabbitListener(queues = "mail.queue")
	public void displayMail(Mail mail) {
		System.out.printf("Received: %s%n", mail);
	}
}
