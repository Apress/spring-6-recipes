package com.apress.spring6recipes.bank.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class OpenIBANValidationClientTest {

	private final OpenIBANValidationClient client = new OpenIBANValidationClient();

	private MockRestServiceServer mockRestServiceServer;

	@BeforeEach
	public void init() {
		mockRestServiceServer = MockRestServiceServer.createServer(client);
	}

	@Test
	public void validIban() {
		var json = new ClassPathResource("NL87TRIO0396451440-result.json");
		var expectedUri = "https://openiban.com/validate/NL87TRIO0396451440?getBIC=true&validateBankCode=true";

		mockRestServiceServer
				.expect(requestTo(expectedUri))
				.andRespond(withSuccess(json, MediaType.APPLICATION_JSON));

		var result = client.validate("NL87TRIO0396451440");
		assertTrue(result.valid());
	}

	@Test
	public void invalidIban() {
		var expectedUri = "https://openiban.com/validate/NL28XXXX389242218?getBIC=true&validateBankCode=true";
		var json = new ClassPathResource("NL28XXXX389242218-result.json");
		mockRestServiceServer
				.expect(requestTo(expectedUri))
				.andRespond(withSuccess(json, MediaType.APPLICATION_JSON));

		var result = client.validate("NL28XXXX389242218");
		assertFalse(result.valid());
	}
}