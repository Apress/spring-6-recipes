package com.apress.spring6recipes.nosql;

import org.neo4j.driver.GraphDatabase;

import java.util.Map;

public class Main {

	private static final String URL = "bolt://localhost:7687";
	private static final String CREATE_CHARACTER_QUERY = "CREATE (:Character {name: $name})";
	private static final String CREATE_PLANET_QUERY = "CREATE (:Planet {name: $name})";
	private static final String CREATE_PLANET_REL_QUERY = "MATCH (a:Character), (b:Planet) WHERE a.name=$cname AND b.name=$pname CREATE (a)-[r:LOCATION]->(b)";
	private static final String CREATE_FRIENDS_REL_QUERY = "MATCH (a:Character), (b:Character) WHERE a.name=$aname AND b.name=$bname CREATE (a)-[r:FRIENDS_WITH]->(b)";
	private static final String CREATE_MASTER_REL_QUERY = "MATCH (a:Character), (b:Character) WHERE a.name=$aname AND b.name=$bname CREATE (a)-[r:MASTER_OF]->(b)";
	private static final String DELETE_ALL_QUERY = "MATCH (n) DETACH DELETE n";


	public static void main(String[] args) {

		try (var driver = GraphDatabase.driver(URL)) {
			try (var session = driver.session()) {

				// Characters
				session.run(CREATE_CHARACTER_QUERY, Map.of("name", "Yoda"));
				session.run(CREATE_CHARACTER_QUERY, Map.of("name", "Luke Skywalker"));
				session.run(CREATE_CHARACTER_QUERY, Map.of("name", "Leia Organa"));
				session.run(CREATE_CHARACTER_QUERY, Map.of("name", "Han Solo"));
				// Planets
				session.run(CREATE_PLANET_QUERY, Map.of("name", "Dagobah"));
				session.run(CREATE_PLANET_QUERY, Map.of("name", "Tatooine"));
				session.run(CREATE_PLANET_QUERY, Map.of("name", "Alderaan"));

				// Relations
				session.run(CREATE_PLANET_REL_QUERY, Map.of("cname", "Yoda", "pname", "Dagobah"));
				session.run(CREATE_PLANET_REL_QUERY, Map.of("cname", "Leia Organa", "pname", "Alderaan"));
				session.run(CREATE_PLANET_REL_QUERY, Map.of("cname", "Luke Skywalker", "pname", "Tatooine"));

				session.run(CREATE_FRIENDS_REL_QUERY, Map.of("aname", "Luke Skywalker", "bname", "Han Solo"));
				session.run(CREATE_FRIENDS_REL_QUERY, Map.of("aname", "Leia Organa", "bname", "Han Solo"));
				session.run(CREATE_FRIENDS_REL_QUERY, Map.of("aname", "Leia Organa", "bname", "Luke Skywalker"));

				session.run(CREATE_MASTER_REL_QUERY, Map.of("aname", "Yoda", "bname", "Luke Skywalker"));
			}

			try (var session = driver.session()) {
				var result = session.run("MATCH (n) RETURN n.name as name");
				result.stream()
								.flatMap(m -> m.fields().stream())
								.map(row -> row.key() + " : " + row.value().asString())
								.forEach(System.out::println);

				session.run(DELETE_ALL_QUERY);
			}
		}
	}
}
