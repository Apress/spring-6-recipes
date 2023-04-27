package com.apress.spring6recipes.post;

import org.springframework.jms.annotation.JmsListener;

public class MailListener {

	@JmsListener(destination = "mail.queue")
	public void displayMail(Mail mail) {
		System.out.printf("Received: %s%n", mail);
	}
}
