package com.apress.spring6recipes.post;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.ConnectionFactory;

public class FrontDeskImpl implements FrontDesk {

	private static final String QUEUE_NAME = "mail.queue";

	public void sendMail(final Mail mail) {
		var connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		connectionFactory.setPort(5672);

		try (var connection = connectionFactory.newConnection();
				 var channel = connection.createChannel()) {
			channel.queueDeclare(QUEUE_NAME, true, false, false, null);
			var message = new ObjectMapper().writeValueAsString(mail);
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
			System.out.printf("Send: %s%n", message);
		} catch (IOException | TimeoutException ex) {
			throw new RuntimeException(ex);
		}
	}
}
