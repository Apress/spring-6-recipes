package com.apress.spring6recipes.nosql;

import org.neo4j.driver.GraphDatabase;

import java.util.stream.Collectors;

import static org.neo4j.driver.Values.parameters;

public class Main {

	private static final String URL = "bolt://localhost:7687";
	private static final String CREATE_QUERY = "CREATE (:Greetings {msg: $msg})";
	private static final String MATCH_QUERY = "MATCH (g) RETURN g.msg";
	private static final String DELETE_ALL_QUERY = "MATCH (n) DETACH DELETE n";

	public static void main(String[] args) {

		try (var driver = GraphDatabase.driver(URL)) {
			try (var session = driver.session()) {
				session.writeTransaction(tx -> {
					tx.run(CREATE_QUERY, parameters("msg", "Hello"));
					tx.run(CREATE_QUERY, parameters("msg", "World"));
					return null;
				});

				var readResult = session.readTransaction(tx -> {
					var res = tx.run(MATCH_QUERY);
					return res.stream().map(it -> it.get(0).asString()).collect(Collectors.joining(" "));
				});

				System.out.println("After Read: \n\t" + readResult);

				session.run(DELETE_ALL_QUERY);
			}
		}
	}
}
