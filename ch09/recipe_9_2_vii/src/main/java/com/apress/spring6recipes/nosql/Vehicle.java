package com.apress.spring6recipes.nosql;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("vehicles")
public record Vehicle (String id, @Indexed String vehicleNo, String color, int wheel, int seat) {

	public Vehicle(String vehicleNo, String color, int wheel, int seat) {
		this(UUID.randomUUID().toString(), vehicleNo, color, wheel, seat);
	}
}
