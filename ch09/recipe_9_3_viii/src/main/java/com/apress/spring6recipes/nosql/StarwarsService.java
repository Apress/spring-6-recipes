package com.apress.spring6recipes.nosql;

import reactor.core.publisher.Mono;

public interface StarwarsService {

	Mono<Planet> save(Planet planet);
	Mono<Character> save(Character charachter);
	Mono<Void> printAll();
	Mono<Void> deleteAll();

}
