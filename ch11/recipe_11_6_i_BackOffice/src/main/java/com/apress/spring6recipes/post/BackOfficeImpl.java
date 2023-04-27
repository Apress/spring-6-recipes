package com.apress.spring6recipes.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class BackOfficeImpl implements BackOffice {

	private static final String QUEUE_NAME = "mail.queue";

	private MailListener mailListener = new MailListener();

	@Override
	public Mail receiveMail() {

		var connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		connectionFactory.setPort(5672);

		try (var connection = connectionFactory.newConnection();
			var channel = connection.createChannel() ) {
			channel.queueDeclare(QUEUE_NAME, true, false, false, null);

			var consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
								throws IOException {
					var mail = new ObjectMapper().readValue(body, Mail.class);
					mailListener.displayMail(mail);
				}
			};
			channel.basicConsume(QUEUE_NAME, true, consumer);

		} catch (IOException | TimeoutException e) {
			throw new RuntimeException(e);
		}

		return null;
	}
}
