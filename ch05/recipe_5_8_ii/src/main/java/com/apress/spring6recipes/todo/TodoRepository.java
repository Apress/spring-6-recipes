package com.apress.spring6recipes.todo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TodoRepository {

	Flux<Todo> findAll();
	Mono<Todo> findOne(long id);
	Mono<Void> remove(long id);
	Mono<Todo> save(Todo todo);
	Flux<Todo> findByOwner(String author);

}
