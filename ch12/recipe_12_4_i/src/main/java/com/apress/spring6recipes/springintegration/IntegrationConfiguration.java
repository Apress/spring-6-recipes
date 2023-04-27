package com.apress.spring6recipes.springintegration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.dsl.Files;

import java.io.File;
import java.time.Duration;

@Configuration
@EnableIntegration
public class IntegrationConfiguration {

  @Bean
  public InboundHelloWorldFileMessageProcessor messageProcessor() {
      return new InboundHelloWorldFileMessageProcessor();
  }

  @Bean
  public IntegrationFlow inboundFileFlow(
				@Value("${user.home}/inboundFiles/new/") File directory) {
    return IntegrationFlow
            .from(
                Files.inboundAdapter(directory).patternFilter("*.csv"),
                c -> c.poller(Pollers.fixedRate(Duration.ofSeconds(10))))
            .handle(messageProcessor())
            .get();
  }
}
