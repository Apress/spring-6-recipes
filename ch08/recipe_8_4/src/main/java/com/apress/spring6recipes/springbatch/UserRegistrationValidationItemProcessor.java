package com.apress.spring6recipes.springbatch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collection;

public class UserRegistrationValidationItemProcessor
				implements ItemProcessor<UserRegistration, UserRegistration> {

	private final Collection<String> states =  Arrays.asList((
					"AL AK AS AZ AR CA CO CT DE DC FM " +
					"FL GA GU HI ID IL IN IA KS KY LA ME MH MD " +
					"MA MI MN MS MO MT NE NV NH NJ NM NY NC ND " +
					"MP OH OK OR PW PA PR RI SC SD TN TX UT " +
					"VT VI VA WA WV WI WY").split(" "));

	private String stripNonNumbers(String input) {
		var output = input == null ? "" : input;
		var numbersOnly = new StringBuilder();
		for (int i = 0; i < output.length(); i++) {
			char potentialDigit = output.charAt(i);
			if (Character.isDigit(potentialDigit)) {
				numbersOnly.append(potentialDigit);
			}
		}
		return numbersOnly.toString();
	}

	private boolean isTelephoneValid(String telephone) {
		return StringUtils.hasText(telephone) && telephone.length() == 10;
	}

	private boolean isZipCodeValid(String zip) {
		return StringUtils.hasText(zip) && ((zip.length() == 5) || (zip.length() == 9));
	}

	private boolean isValidState(String state) {
		return StringUtils.hasText(state) && states.contains(state.trim());
	}

	public UserRegistration process(UserRegistration input) {
		var zipCode = stripNonNumbers(input.zip());
		var telephone = stripNonNumbers(input.phoneNumber());
		var state = input.state();

		if (isTelephoneValid(telephone) && isZipCodeValid(zipCode) && isValidState(state)) {
			return new UserRegistration(
							input.firstName(), input.lastName(), input.company(), input.address(),
							input.city(), input.state(), zipCode, input.county(), input.url(),
							telephone, input.fax());
		}
		return null;
	}
}
