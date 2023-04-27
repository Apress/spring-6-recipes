package com.apress.spring6recipes.nosql;

import org.neo4j.driver.Driver;

import java.util.Map;

class Neo4jStarwarsRepository implements StarwarsRepository {

	private static final String CREATE_PLANET_QUERY = "CREATE (a:Planet {id: randomUUID(), name: $name}) RETURN a.id";
	private static final String CREATE_CHARACTER_QUERY = "CREATE (a:Character {id: randomUUID(), name: $name}) RETURN a.id";

	private static final String CREATE_PLANET_REL_QUERY = "MATCH (a:Character), (b:Planet) WHERE a.id=$aid AND b.id=$bid CREATE (a)-[r:LOCATION]->(b)";
	private static final String CREATE_FRIENDS_REL_QUERY = "MATCH (a:Character), (b:Character) WHERE a.id=$aid AND b.id=$bid CREATE (a)-[r:FRIENDS_WITH]->(b)";
	private static final String CREATE_MASTER_REL_QUERY = "MATCH (a:Character), (b:Character) WHERE a.id=$aid AND b.id=$bid CREATE (a)-[r:MASTER_OF]->(b)";

	private final Driver db;

	Neo4jStarwarsRepository(Driver db) {
		this.db = db;
	}

	@Override
	public Planet save(Planet planet) {
		if (planet.getId() != null) {
			return planet;
		}

		try (var session = db.session()) {
			var res = session.run(CREATE_PLANET_QUERY, Map.of("name", planet.getName()));
			planet.setId(res.single().get(0).asString());
			return planet;
		}
	}

	@Override
	public Character save(Character charr) {
		if (charr.getId() != null) {
			return charr;
		}

		try (var session = db.session()) {
			session.executeWrite(tx -> {
				var res = tx.run(CREATE_CHARACTER_QUERY, Map.of( "name", charr.getName()));
				charr.setId(res.single().get(0).asString());
				if (charr.getLocation() != null) {
					var location = save(charr.getLocation());
					tx.run(CREATE_PLANET_REL_QUERY, Map.of("aid", charr.getId(),
																								 "bid", location.getId()));
				}
				for (var friend : charr.getFriends()) {
					friend = save(friend);
					tx.run(CREATE_FRIENDS_REL_QUERY, Map.of("aid", charr.getId(),
																									"bid", friend.getId()));
				}

				if (charr.getApprentice() != null) {
					var apprentice = save(charr.getApprentice());
					tx.run(CREATE_MASTER_REL_QUERY, Map.of("aid", charr.getId(),
																								 "bid", apprentice.getId()));
				}
				return null;
			});
		}
		return charr;
	}
}
