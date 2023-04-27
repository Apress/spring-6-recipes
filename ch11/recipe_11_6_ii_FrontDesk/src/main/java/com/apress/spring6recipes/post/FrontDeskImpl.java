package com.apress.spring6recipes.post;

import org.springframework.amqp.rabbit.core.RabbitGatewaySupport;

public class FrontDeskImpl extends RabbitGatewaySupport implements FrontDesk {

    public void sendMail(final Mail mail) {
        getRabbitOperations().convertAndSend(mail);
    }

}
