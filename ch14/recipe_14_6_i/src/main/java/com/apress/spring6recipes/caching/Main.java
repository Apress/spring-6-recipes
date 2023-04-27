package com.apress.spring6recipes.caching;

import com.apress.spring6recipes.caching.config.CustomerConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StopWatch;

public class Main {

	public static void main(String[] args) {
		var cfg = CustomerConfiguration.class;
		try (var context = new AnnotationConfigApplicationContext(cfg)) {
			var customerRepository = context.getBean(CustomerRepository.class);
			var sw = new StopWatch("Cache Evict and Put");

			sw.start("Get 'Unknown Customer'");
			var customer = customerRepository.find(System.currentTimeMillis());
			System.out.println("Get 'Unknown Customer' (result) : " + customer);
			sw.stop();

			sw.start("Create New Customer");
			customer = customerRepository.create("Marten Deinum");
			System.out.println("Create new Customer (result) : " + customer);
			sw.stop();

			long customerId = customer.id();

			sw.start("Get 'New Customer 1'");
			customer = customerRepository.find(customerId);
			System.out.println("Get 'New Customer 1' (result) : " + customer);
			sw.stop();

			sw.start("Get 'New Customer 2'");
			customer = customerRepository.find(customerId);
			System.out.println("Get 'New Customer 2' (result) : " + customer);
			sw.stop();

			sw.start("Update Customer");
			customer = new Customer(customer.id(), "Josh Long");
			customerRepository.update(customer);
			sw.stop();

			sw.start("Get 'Updated Customer 1'");
			customer = customerRepository.find(customerId);
			System.out.println("Get 'Updated Customer 1' (result) : " + customer);
			sw.stop();

			sw.start("Get 'Updated Customer 2'");
			customer = customerRepository.find(customerId);
			System.out.println("Get 'Updated Customer 2' (result) : " + customer);
			sw.stop();

			sw.start("Remove Customer");
			customerRepository.remove(customer.id());
			sw.stop();

			sw.start("Get 'Deleted Customer 1'");
			customer = customerRepository.find(customerId);
			System.out.println("Get 'Deleted Customer 1' (result) : " + customer);
			sw.stop();

			sw.start("Get 'Deleted Customer 2'");
			customer = customerRepository.find(customerId);
			System.out.println("Get 'Deleted Customer 2' (result) : " + customer);
			sw.stop();

			System.out.println();
			System.out.println(sw.prettyPrint());
		}
	}
}
