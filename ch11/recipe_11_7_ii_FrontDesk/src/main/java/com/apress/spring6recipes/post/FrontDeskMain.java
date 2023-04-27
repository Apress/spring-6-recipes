package com.apress.spring6recipes.post;

import com.apress.spring6recipes.post.config.FrontOfficeConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class FrontDeskMain {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context =
            new AnnotationConfigApplicationContext(FrontOfficeConfiguration.class);
        context.registerShutdownHook();

        FrontDesk frontDesk = context.getBean(FrontDesk.class);
        frontDesk.sendMail(new Mail("1234", "US", 1.5));

        System.in.read();

    }


}
