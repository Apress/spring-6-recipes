package com.apress.spring6recipes.replicator;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class EmailErrorNotifier implements ErrorNotifier {

	private static final String MSG = """
					       Dear Administrator,
					       An error occurred when copying the following file :
					       Source directory: %s
					       Destination directory: %s
					       Filename : %s
					""";

	private final MailSender mailSender;

	public EmailErrorNotifier(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public void notifyCopyError(String srcDir, String destDir, String filename) {
		var message = new SimpleMailMessage();
		message.setFrom("system@localhost");
		message.setTo("admin@localhost");
		message.setSubject("File Copy Error");
		message.setText(String.format(MSG, srcDir, destDir, filename));
		mailSender.send(message);
	}
}
