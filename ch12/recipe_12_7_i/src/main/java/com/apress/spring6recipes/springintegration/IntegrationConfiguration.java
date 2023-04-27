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
  public CustomerBatchFileSplitter splitter() {
      return new CustomerBatchFileSplitter();
  }

  @Bean
  public CustomerDeletionServiceActivator customerDeletionServiceActivator() {
      return new CustomerDeletionServiceActivator();
  }

  @Bean
  public IntegrationFlow fileSplitAndDelete(
					@Value("file:${user.home}/customerstoremove/new/") File inputDirectory) {

		var poller = Pollers.fixedRate(Duration.ofSeconds(1));
    return IntegrationFlow.from(
            Files.inboundAdapter(inputDirectory)
		                .patternFilter("customerstoremove-*.txt"), c -> c.poller(poller))
            .split(splitter())
            .handle(customerDeletionServiceActivator())
            .get();
  }
}
