package com.apress.spring6recipes.board;

import java.util.List;
import java.util.Optional;

public interface TodoService {

	List<Todo> listTodos();
	void save(Todo todo);
	void complete(long id);
	void remove(long id);
	Optional<Todo> findById(long id);

}
