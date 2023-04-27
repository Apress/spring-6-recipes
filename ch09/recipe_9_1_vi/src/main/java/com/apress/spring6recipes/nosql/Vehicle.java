package com.apress.spring6recipes.nosql;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "vehicles")
public record Vehicle(String id, String vehicleNo, String color, int wheel, int seat) {
}
