package com.apress.spring6recipes.post;

import jakarta.jms.JMSException;
import jakarta.jms.JMSRuntimeException;
import jakarta.jms.MapMessage;
import jakarta.jms.Message;
import jakarta.jms.Session;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;

public class BackOfficeImpl implements BackOffice {

	public Mail receiveMail() {
		try (var cf = new ActiveMQConnectionFactory("tcp://localhost:61616");
				 var ctx = cf.createContext(Session.AUTO_ACKNOWLEDGE)) {

			var destination = new ActiveMQQueue("mail.queue");
			var consumer = ctx.createConsumer(destination);
			var message = consumer.receive();
			return convert(message);
		} catch (JMSException ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		}
	}

	private Mail convert(Message msg) throws JMSException {
		var message = (MapMessage) msg;
		return new Mail(message.getString("mailId"),
						message.getString("country"),
						message.getDouble("weight"));
	}
}
