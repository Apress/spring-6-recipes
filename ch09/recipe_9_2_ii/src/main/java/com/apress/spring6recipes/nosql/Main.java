package com.apress.spring6recipes.nosql;

import redis.clients.jedis.Jedis;

import static redis.clients.jedis.Protocol.DEFAULT_PORT;

public class Main {

	public static void main(String[] args) {
		try (var jedis = new Jedis("localhost", DEFAULT_PORT)) {
			jedis.rpush("authors", "Marten Deinum", "Josh Long", "Daniel Rubio", "Gary Mak");
			System.out.println("Authors: " + jedis.lrange("authors", 0, -1));

			jedis.hset("sr_5", "authors", "Josh Long, Marten Deinum");
			jedis.hset("sr_5", "published", "2019");

			jedis.hset("sr_6", "authors", "Josh Long, Marten Deinum");
			jedis.hset("sr_6", "published", "2023");

			System.out.println("Spring 5 Recipes " + jedis.hgetAll("sr_5"));
			System.out.println("Spring 6 Recipes " + jedis.hgetAll("sr_6"));
		}

	}
}
