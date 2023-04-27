package com.apress.spring6recipes.reactive.court;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		var url = "http://localhost:8080/";

		WebClient.create(url).get().uri("/members")
						.accept(MediaType.APPLICATION_JSON)
						.exchangeToFlux( (cr) -> cr.bodyToFlux(String.class))
						.subscribe(System.out::println);

		System.in.read();
	}
}
