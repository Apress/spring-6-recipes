package com.apress.spring6recipes.court;

import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class Main {

	public static void main(String[] args) {
		var uri = "http://localhost:8080/court/members/{memberId}";
		var params = Map.of("memberId", "1");
		var restTemplate = new RestTemplate();
		var result = restTemplate.getForObject(uri, String.class, params);
		System.out.println(result);
	}
}
