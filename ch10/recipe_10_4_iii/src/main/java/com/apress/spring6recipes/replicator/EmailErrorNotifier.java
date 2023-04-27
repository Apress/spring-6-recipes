package com.apress.spring6recipes.replicator;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class EmailErrorNotifier implements ErrorNotifier {

	private final MailSender mailSender;
	private final SimpleMailMessage template;

	public EmailErrorNotifier(MailSender mailSender, SimpleMailMessage template) {
		this.mailSender = mailSender;
		this.template = template;
	}

	@Override
	public void notifyCopyError(String srcDir, String destDir, String filename) {
		var message = new SimpleMailMessage(template);
		message.setText(String.format(
						template.getText(), srcDir, destDir, filename));
		mailSender.send(message);
	}
}
