package com.apress.spring6recipes.nosql;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CountDownLatch;

public class Main {

	public static void main(String[] args) throws Exception {

		try (var ctx = new AnnotationConfigApplicationContext(StarwarsConfig.class)) {
			var service = ctx.getBean(StarwarsService.class);
			// Planets
			var dagobah = new Planet("Dagobah");
			var alderaan = new Planet("Alderaan");
			var tatooine = new Planet("Tatooine");

			var planets = Flux.just(dagobah, alderaan, tatooine);

			// Characters
			var han = new Character("Han Solo");
			var leia = new Character("Leia Organa");
			var luke = new Character("Luke Skywalker");
			var yoda = new Character("Yoda");

			leia.setLocation(alderaan);
			leia.addFriend(han);

			luke.setLocation(tatooine);
			luke.addFriend(han);
			luke.addFriend(leia);

			yoda.setLocation(dagobah);
			yoda.setApprentice(luke);

			var characters = Flux.just(han, luke, leia, yoda);
			var countDownLatch = new CountDownLatch(1);
			planets.flatMap(service::save)
							.thenMany(characters.concatMap(service::save))
							.then(service.printAll())
							.then(service.deleteAll())
							.doOnTerminate(countDownLatch::countDown).subscribe();

			countDownLatch.await();
		}
	}
}
