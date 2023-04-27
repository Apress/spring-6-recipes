package com.apress.spring6recipes.springbatch;

public record UserRegistration(

				String firstName,
				String lastName,
				String company,
				String address,
				String city,
				String state,
				String zip,
				String county,
				String url,
				String phoneNumber,
				String fax) {
}
