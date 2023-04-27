package com.apress.spring6recipes.springintegration;

import org.springframework.integration.annotation.ServiceActivator;

import java.util.Collection;

public class SummaryServiceActivator {

    @ServiceActivator
    public void summary(Collection<Customer> customers) {
        System.out.printf("Removed %s customers.%n", customers.size());
    }
}
