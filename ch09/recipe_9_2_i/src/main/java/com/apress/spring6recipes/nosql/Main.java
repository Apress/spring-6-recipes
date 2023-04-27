package com.apress.spring6recipes.nosql;

import redis.clients.jedis.Jedis;

import static redis.clients.jedis.Protocol.DEFAULT_PORT;

public class Main {

	public static void main(String[] args) {
		try (var jedis = new Jedis("localhost", DEFAULT_PORT)) {
			jedis.set("msg", "Hello World, from Redis!");
			System.out.println(jedis.get("msg"));
		}
	}
}
