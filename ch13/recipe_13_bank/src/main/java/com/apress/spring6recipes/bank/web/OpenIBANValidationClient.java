package com.apress.spring6recipes.bank.web;

import org.springframework.stereotype.Service;
import org.springframework.web.client.support.RestGatewaySupport;

@Service
class OpenIBANValidationClient extends RestGatewaySupport
				implements IBANValidationClient {

	private static final String URL_TEMPLATE =
		"https://openiban.com/validate/{IBAN_NUMBER}?getBIC=true&validateBankCode=true";

	@Override
	public IBANValidationResult validate(String iban) {

		return getRestTemplate()
						.getForObject(URL_TEMPLATE, IBANValidationResult.class, iban);
	}
}