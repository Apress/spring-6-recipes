package com.apress.spring6recipes.replicator;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailErrorNotifier implements ErrorNotifier {

	private final JavaMailSender mailSender;
	private final SimpleMailMessage template;

	public EmailErrorNotifier(JavaMailSender mailSender,
														SimpleMailMessage template) {
		this.mailSender = mailSender;
		this.template = template;
	}

	@Override
	public void notifyCopyError(String srcDir, String destDir, String filename) {
		var message = mailSender.createMimeMessage();
		try {
			var helper = new MimeMessageHelper(message, true);
			helper.setFrom(template.getFrom());
			helper.setTo(template.getTo());

			helper.setSubject(template.getSubject());
			helper.setText(String.format(
							template.getText(), srcDir, destDir, filename));

			helper.addAttachment("beans.xml", new ClassPathResource("beans.xml"));
		} catch (MessagingException e) {
			throw new MailParseException(e);
		}
		mailSender.send(message);
	}
}
