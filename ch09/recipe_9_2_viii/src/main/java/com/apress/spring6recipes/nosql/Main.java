package com.apress.spring6recipes.nosql;

import java.util.concurrent.CountDownLatch;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.spring6recipes.nosql.config.RedisConfig;

import reactor.core.publisher.Flux;

public class Main {

	private static final String COUNT = "Number of Vehicles: %d%n";

	public static void main(String[] args) throws Exception {
		var cfg = RedisConfig.class;
		try (var ctx = new AnnotationConfigApplicationContext(cfg)) {
			var repository = ctx.getBean(VehicleRepository.class);
			var countDownLatch = new CountDownLatch(1);

			var vehicles = Flux.just(
							new Vehicle("TEM0001", "RED", 4, 4),
							new Vehicle("TEM0002", "RED", 4, 4));

			repository.count().doOnSuccess(cnt -> System.out.printf(COUNT, cnt))
				.thenMany(vehicles.flatMap(repository::save))
							.then(repository.count()).doOnSuccess(cnt -> System.out.printf(COUNT, cnt))
							.then(repository.findByVehicleNo("TEM0001")).doOnSuccess(System.out::println)
							.doOnTerminate(countDownLatch::countDown)
							.then(repository.count()).subscribe(cnt -> System.out.printf(COUNT, cnt));

			countDownLatch.await();
		}
	}
}
