package com.apress.spring6recipes.nosql;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import reactor.core.publisher.Flux;

import java.util.concurrent.CountDownLatch;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		try (var ctx = new AnnotationConfigApplicationContext(CouchbaseConfiguration.class)) {
			var repository = ctx.getBean(VehicleRepository.class);

			var countDownLatch = new CountDownLatch(1);

			repository.saveAll(Flux.just(new Vehicle("TEM0001", "GREEN", 3, 1), //
											new Vehicle("TEM0004", "RED", 4, 2)))
							.last()
							.then(repository.findById("TEM0001")).doOnSuccess(System.out::println)
							.then(repository.findById("TEM0004")).doOnSuccess(System.out::println)
							.then(repository.deleteById(Flux.just("TEM0001", "TEM00004")))
							.doOnTerminate(countDownLatch::countDown)
							.subscribe();

			countDownLatch.await();
		}
	}
}
