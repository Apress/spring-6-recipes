package com.apress.spring6recipes.board;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
class TodoServiceImpl implements TodoService {

	private final TodoRepository todoRepository;

	TodoServiceImpl(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	@Override
	public List<Todo> listTodos() {
		return todoRepository.findAll();
	}

	@Override
	public void save(Todo todo) {
		this.todoRepository.save(todo);
	}

	@Override
	public void complete(long id) {
		findById(id)
						.ifPresent((todo) -> {
							todo.setCompleted(true);
							todoRepository.save(todo);
						});
	}

	@Override
	public void remove(long id) {
		todoRepository.remove(id);
	}

	@Override
	public Optional<Todo> findById(long id) {
		return todoRepository.findOne(id);
	}
}
