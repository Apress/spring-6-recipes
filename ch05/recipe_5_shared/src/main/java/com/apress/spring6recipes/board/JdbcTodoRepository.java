package com.apress.spring6recipes.board;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
class JdbcTodoRepository implements TodoRepository {

	private final JdbcTemplate jdbc;

	JdbcTodoRepository(DataSource dataSource) {
		this.jdbc = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Todo> findAll() {
		return this.jdbc.query("SELECT * FROM todo ORDER BY id", BeanPropertyRowMapper.newInstance(Todo.class));
	}

	@Override
	public List<Todo> findByOwner(String owner) {
		return this.jdbc.query("SELECT * FROM todo WHERE owner=? ORDER BY id",
				BeanPropertyRowMapper.newInstance(Todo.class), owner);
	}

	@Override
	public Optional<Todo> findOne(long id) {
		var todo = this.jdbc.queryForObject("SELECT * FROM todo WHERE id=?", BeanPropertyRowMapper.newInstance(Todo.class),
				id);
		return Optional.ofNullable(todo);
	}

	@Override
	public Todo save(Todo todo) {

		if (todo.getId() == null) {
			var sql = "INSERT INTO todo (owner, description, completed) VALUES (?,?,?)";
			var keyHolder = new GeneratedKeyHolder();

			this.jdbc.update(con -> {
				var ps = con.prepareStatement(sql, new String[] { "id" });
				ps.setString(1, todo.getOwner());
				ps.setString(2, todo.getDescription());
				ps.setBoolean(3, todo.isCompleted());
				return ps;
			}, keyHolder);

			todo.setId(keyHolder.getKey().longValue());
		}
		else {
			var sql = "UPDATE todo SET owner=?, description=?, completed=? WHERE id=?";
			this.jdbc.update(sql, todo.getOwner(), todo.getDescription(), todo.isCompleted(), todo.getId());
		}
		return todo;
	}

	@Override
	public void remove(long id) {
		this.jdbc.update("DELETE FROM todo WHERE id=?", id);
	}

}
