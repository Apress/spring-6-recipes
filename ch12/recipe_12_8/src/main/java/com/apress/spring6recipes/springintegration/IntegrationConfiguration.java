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
	public LineToCustomerTransformer transformer() {
		return new LineToCustomerTransformer();
	}

	@Bean
	public IntegrationFlow safeCustomerFlow() {
		return IntegrationFlow.from("safeCustomerChannel").
						<Customer>log(c -> "Safe: " + c).get();
	}

	@Bean
	public IntegrationFlow riskyCustomerFlow() {
		return IntegrationFlow.from("riskyCustomerChannel").
						<Customer>log(c -> "Risky: " + c).get();
	}

	@Bean
	public CustomerCreditScoreRouter customerCreditScoreRouter() {
		return new CustomerCreditScoreRouter();
	}

	@Bean
	public IntegrationFlow fileSplitAndDelete(@Value("file:${user.home}/customerstoimport/new/") File inputDirectory) {
		var poller = Pollers.fixedRate(Duration.ofSeconds(1));
		return IntegrationFlow.from(
									Files.inboundAdapter(inputDirectory)
													.patternFilter("customers-*.txt"), c -> c.poller(poller))
					.split(splitter())
					.transform(transformer())
					.route(customerCreditScoreRouter())
					.get();
	}
}