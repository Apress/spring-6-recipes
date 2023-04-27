package com.apress.spring6recipes.springintegration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;

@Configuration
@EnableIntegration
public class AdditionConfiguration {

    @Bean
    public AdditionService additionService() {
        return new AdditionService();
    }

    @Bean
    public IntegrationFlow additionFlow() {
        return IntegrationFlow.from("request")
                .handle(additionService(), "add")
                .channel("response").get();
    }
}

