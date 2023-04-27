package com.apress.spring6recipes.bank.web;

public interface IBANValidationClient {

	IBANValidationResult validate(String iban);

}
