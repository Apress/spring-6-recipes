package com.apress.spring6recipes.nosql;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

//@RedisHash("vehicles")
public record Vehicle (String vehicleNo, String color, int wheel, int seat)
  implements Serializable { }
