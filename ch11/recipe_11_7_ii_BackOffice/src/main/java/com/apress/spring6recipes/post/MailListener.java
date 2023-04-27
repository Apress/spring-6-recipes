package com.apress.spring6recipes.post;

import org.springframework.kafka.annotation.KafkaListener;

public class MailListener {

    @KafkaListener(topics = "mails")
    public void displayMail(Mail mail) {
        System.out.printf(" Received: %s%n", mail);
    }
}
