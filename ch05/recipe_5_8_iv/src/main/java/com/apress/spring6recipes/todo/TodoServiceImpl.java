package com.apress.spring6recipes.todo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
class TodoServiceImpl implements TodoService {

	private final TodoRepository todoRepository;

	TodoServiceImpl(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	@Override
	public Flux<Todo> listTodos() {
		return todoRepository.findAll();
	}

	@Override
	public Mono<Todo> save(Todo todo) {
		this.todoRepository.save(todo);
		return null;
	}

	@Override
	public Mono<Void> complete(long id) {
		return findById(id)
						.flatMap((todo) -> {
							todo.setCompleted(true);
							return todoRepository.save(todo);
						}).then();
	}

	@Override
	public Mono<Void> remove(long id) {
		return todoRepository.remove(id);
	}

	@Override
	public Mono<Todo> findById(long id) {
		return todoRepository.findOne(id);
	}
}
