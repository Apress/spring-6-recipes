package com.apress.spring6recipes.bank.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record IBANValidationResult(
	boolean valid,
	List<String> messages,
	String iban,
	Map<String, String> bankData) {
}
