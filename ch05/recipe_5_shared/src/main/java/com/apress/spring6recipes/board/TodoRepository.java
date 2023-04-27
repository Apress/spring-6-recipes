package com.apress.spring6recipes.board;

import java.util.List;
import java.util.Optional;

public interface TodoRepository {

	List<Todo> findAll();
	Optional<Todo> findOne(long id);
	void remove(long id);
	Todo save(Todo todo);
	List<Todo> findByOwner(String author);

}
