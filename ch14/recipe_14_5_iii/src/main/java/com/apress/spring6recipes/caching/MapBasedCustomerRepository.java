package com.apress.spring6recipes.caching;

import com.apress.spring6recipes.utils.Utils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MapBasedCustomerRepository implements CustomerRepository {

	private final AtomicLong idGenerator = new AtomicLong();
	private final Map<Long, Customer> customers = new ConcurrentHashMap<>();

	@Override
	@Cacheable(value = "customers")
	public Customer find(long customerId) {
		Utils.sleep(500);
		return customers.get(customerId);
	}

	@Override
	public Customer create(String name) {
		var id = idGenerator.incrementAndGet();
		return customers.computeIfAbsent(id, key -> new Customer(key, name));
	}

	@Override
	@CacheEvict(value="customers", key = "#customer.id")
	public void update(Customer customer) {
		customers.put(customer.id(), customer);
	}

	@Override
	@CacheEvict("customers")
	public void remove(long customerId) {
		customers.remove(customerId);
	}
}
