package com.apress.spring6recipes.springintegration;

import org.springframework.integration.annotation.Router;

public class CustomerCreditScoreRouter {

    @Router
    public String routeByCustomerCreditScore(Customer customer) {
        if (customer.creditScore() > 770) {
            return "safeCustomerChannel";
        } else {
            return "riskyCustomerChannel";
        }
    }
}
