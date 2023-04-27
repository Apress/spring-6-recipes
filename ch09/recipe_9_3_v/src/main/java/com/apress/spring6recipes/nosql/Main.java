package com.apress.spring6recipes.nosql;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {

		try (var ctx = new AnnotationConfigApplicationContext(StarwarsConfig.class)) {
			var repository = ctx.getBean(StarwarsRepository.class);

			// Planets
			var dagobah = new Planet("Dagobah");
			var alderaan = new Planet("Alderaan");
			var tatooine = new Planet("Tatooine");

			Stream.of(dagobah, alderaan, tatooine).forEach(repository::save);

			// Characters
			var han = new Character("Han Solo");
			var leia = new Character("Leia Organa");
			leia.setLocation(alderaan);
			leia.addFriend(han);

			var luke = new Character("Luke Skywalker");
			luke.setLocation(tatooine);
			luke.addFriend(han);
			luke.addFriend(leia);

			var yoda = new Character("Yoda");
			yoda.setLocation(dagobah);
			yoda.setApprentice(luke);

			Stream.of(han, luke, leia, yoda).forEach(repository::save);

			repository.findAllCharacters().forEach(System.out::println);
			repository.findAllPlanets().forEach(System.out::println);
		}
	}
}
