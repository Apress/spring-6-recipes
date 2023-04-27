package com.apress.spring6recipes.reactive.court.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {

	@GetMapping
	public Mono<String> welcome(@RequestParam(defaultValue = "World") String name) {
		return Mono.just("Hello " + name+" from Spring WebFlux!");
	}
}
