package com.apress.spring6recipes.post;

public class MailListener {

    public void displayMail(Mail mail) {
        System.out.printf("Received: %s%n", mail);
    }
}
