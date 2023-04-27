package com.apress.spring6recipes.todo;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Repository
class R2dbcTodoRepository implements TodoRepository {

	private static final BiFunction<Row, RowMetadata, Todo> ROW_MAPPER = (r, rm) -> {
		var todo = new Todo(r.get("owner", String.class), r.get("description", String.class));
		todo.setCompleted(r.get("completed", Boolean.class));
		todo.setId(r.get("id", Long.class));
		return todo;
	};

	private final DatabaseClient db;

	R2dbcTodoRepository(DatabaseClient db) {
		this.db = db;
	}

	@Override
	public Flux<Todo> findAll() {
		return db.sql("SELECT * FROM todo ORDER BY id")
						.map(ROW_MAPPER).all();
	}

	@Override
	public Flux<Todo> findByOwner(String owner) {
		return db.sql("SELECT * FROM todo WHERE owner=:owner ORDER BY id")
						.bind("owner", owner)
						.map(ROW_MAPPER).all();
	}

	@Override
	public Mono<Todo> findOne(long id) {
		return db.sql("SELECT * FROM todo WHERE id=:id").bind("id", id).map(ROW_MAPPER).first();
	}

	@Override
	public Mono<Todo> save(Todo todo) {
		if (todo.getId() == null) {
			return db.sql("INSERT INTO todo (owner, description, completed) VALUES (:owner,:desc,:completed)")
							.bind("owner", todo.getOwner())
							.bind("desc", todo.getDescription())
							.bind("completed", todo.isCompleted())
							.filter((statement) -> statement.returnGeneratedValues("id"))
							.map((r, rm) -> r.get("id", Long.class))
							.first().map((id) -> {
								todo.setId(id);
								return todo;
							});
		} else {
			return db.sql("UPDATE todo SET owner=:owner, description=:desc, completed=:completed WHERE id=:id")
							.bind("owner", todo.getOwner())
							.bind("desc", todo.getDescription())
							.bind("completed", todo.isCompleted())
							.bind("id", todo.getId())
							.then().then(Mono.just(todo));
		}
	}

	@Override
	public Mono<Void> remove(long id) {
		return db.sql("DELETE FROM todo WHERE id=:id").bind("id", id).then();
	}
}
