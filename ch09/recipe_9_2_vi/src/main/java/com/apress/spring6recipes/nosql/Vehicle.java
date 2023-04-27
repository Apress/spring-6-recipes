package com.apress.spring6recipes.nosql;

import java.io.Serializable;

//@RedisHash("vehicles")
public record Vehicle (String vehicleNo, String color, int wheel, int seat)
  implements Serializable { }
