package com.apress.spring6recipes.post;

import org.springframework.jms.annotation.JmsListener;

import java.util.Map;

public class MailListener {

	@JmsListener(destination = "mail.queue")
	public void displayMail(Map<String, ?> map) {
		var mail = convert(map);
		displayMail(mail);
	}

	private Mail convert(Map<String, ?> msg) {
		return new Mail(
						(String) msg.get("mailId"),
						(String) msg.get("country"),
						(Double) msg.get("weight"));
	}

	private void displayMail(Mail mail) {
		System.out.printf("Received: %s%n", mail);
	}
}
