package com.apress.spring6recipes.court;

import com.apress.spring6recipes.court.domain.Members;
import org.springframework.web.client.RestTemplate;

public class Main {

	public static void main(String[] args) {
		var uri = "http://localhost:8080/court/members";
		var restTemplate = new RestTemplate();
		var result = restTemplate.getForObject(uri, Members.class);
		System.out.println(result);
	}
}
