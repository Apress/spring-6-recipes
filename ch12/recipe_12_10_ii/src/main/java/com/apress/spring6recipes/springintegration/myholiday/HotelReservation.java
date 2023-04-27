package com.apress.spring6recipes.springintegration.myholiday;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public record HotelReservation(String id, String hotelName, double price)
	implements Serializable {

	public HotelReservation(String hotelName, double price) {
		this(UUID.randomUUID().toString(), hotelName, price);
	}
}
