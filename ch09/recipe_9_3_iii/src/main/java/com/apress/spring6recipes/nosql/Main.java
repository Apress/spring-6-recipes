package com.apress.spring6recipes.nosql;

import org.neo4j.driver.GraphDatabase;

import java.util.stream.Stream;

public class Main {

	private static final String URL = "bolt://localhost:7687";
	private static final String DELETE_ALL_QUERY = "MATCH (n) DETACH DELETE n";

	public static void main(String[] args) {

		try (var driver = GraphDatabase.driver(URL)) {
			var repository = new Neo4jStarwarsRepository(driver);

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

			try(var session = driver.session()) {
				var result = session.run("MATCH (n) RETURN n.name as name");
				result.stream()
								.flatMap(m -> m.fields().stream())
								.map(row -> row.key() + " : " + row.value() + ";")
								.forEach(System.out::println);

				session.run(DELETE_ALL_QUERY);

			}
		}
	}
}
