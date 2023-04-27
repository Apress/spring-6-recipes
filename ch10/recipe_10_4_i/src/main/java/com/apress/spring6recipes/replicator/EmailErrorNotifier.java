package com.apress.spring6recipes.replicator;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailErrorNotifier implements ErrorNotifier {

	private static final String MSG = """
					       Dear Administrator,
					       An error occurred when copying the following file :
					       Source directory: %s
					       Destination directory: %s
					       Filename : %s
					""";

	@Override
	public void notifyCopyError(String srcDir, String destDir, String filename) {
		var props = new Properties();
		props.put("mail.smtp.host", "localhost");
		props.put("mail.smtp.port", "3025");
		props.put("mail.smtp.username", "system");
		props.put("mail.smtp.password", "12345");
		var session = Session.getDefaultInstance(props, null);
		try {
			var message = new MimeMessage(session);
			message.setFrom(new InternetAddress("system@localhost"));
			message.setRecipients(Message.RecipientType.TO,
							InternetAddress.parse("admin@localhost"));
			message.setSubject("File Copy Error");
			message.setText(String.format(MSG, srcDir, destDir, filename));
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
