package com.apress.spring6recipes.springintegration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) throws Exception {

        try (var context = new AnnotationConfigApplicationContext(IntegrationConfiguration.class)) {

            System.out.println("Press [Enter] to close.");
            System.in.read();
        }

    }
}
