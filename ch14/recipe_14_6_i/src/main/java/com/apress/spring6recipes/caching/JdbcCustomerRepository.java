package com.apress.spring6recipes.caching;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Statement;

@Repository
@Transactional
public class JdbcCustomerRepository implements CustomerRepository {

	private final JdbcTemplate jdbc;

	public JdbcCustomerRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	@Cacheable(value = "customers")
	public Customer find(long customerId) {
		var sql = "SELECT id, name FROM customer WHERE id=?";
		return jdbc.queryForObject(sql, (rs, rowNum) ->
						new Customer(rs.getLong(1), rs.getString(2)));
	}

	@Override
	@CachePut(value = "customers", key = "#result.id")
	public Customer create(String name) {
		var sql = "INSERT INTO customer (name) VALUES (?);";
		var keyHolder = new GeneratedKeyHolder();
		jdbc.update(con -> {
			var ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, name);
			return ps;
		}, keyHolder);

		return new Customer(keyHolder.getKey().longValue(), name);
	}

	@Override
	@CacheEvict(value = "customers", key = "#customer.id")
	public void update(Customer customer) {
		var sql = "UPDATE customer SET name=? WHERE id=?";
		jdbc.update(sql, customer.name(), customer.id());
	}

	@Override
	@CacheEvict(value = "customers")
	public void remove(long customerId) {
		var sql = "DELETE FROM customer WHERE id=?";
		jdbc.update(sql, customerId);
	}
}
