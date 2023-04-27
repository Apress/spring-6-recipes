package com.apress.spring6recipes.springintegration;

import org.springframework.integration.annotation.ServiceActivator;

public class CustomerDeletionServiceActivator {

    @ServiceActivator
    public void deleteCustomer(String customerId) {
        System.out.printf("the id of the customer to delete is %s%n", customerId);
    }
}
