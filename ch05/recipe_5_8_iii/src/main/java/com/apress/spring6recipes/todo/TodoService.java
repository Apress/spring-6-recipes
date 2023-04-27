package com.apress.spring6recipes.todo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TodoService {

	Flux<Todo> listTodos();
	Mono<Todo> save(Todo todo);
	Mono<Void> complete(long id);
	Mono<Void> remove(long id);
	Mono<Todo> findById(long id);

}
