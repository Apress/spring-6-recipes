package com.apress.spring6recipes.springintegration;

import org.springframework.integration.annotation.Transformer;

public class LineToCustomerTransformer {

    @Transformer
    public Customer transfor(String line) {
        var columns = line.split(",");
        return new Customer(Long.parseLong(columns[0]), columns[1], columns[2], null, Long.parseLong(columns[3]));
    }
}
