package com.apress.spring6recipes.replicator;

import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class EmailErrorNotifier implements ErrorNotifier {

	private final JavaMailSender mailSender;
	private final SimpleMailMessage template;

	public EmailErrorNotifier(JavaMailSender mailSender,
														SimpleMailMessage template) {
		this.mailSender = mailSender;
		this.template = template;
	}

	@Override
	public void notifyCopyError(
					final String srcDir, final String destDir, final String filename) {
		MimeMessagePreparator preparator = (mimeMessage) -> {
			var helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(template.getFrom());
			helper.setTo(template.getTo());
			helper.setSubject(template.getSubject());
			helper.setText(String.format(
							template.getText(), srcDir, destDir, filename));

			helper.addAttachment("beans.xml", new ClassPathResource("beans.xml"));
		};
		mailSender.send(preparator);
	}
}
