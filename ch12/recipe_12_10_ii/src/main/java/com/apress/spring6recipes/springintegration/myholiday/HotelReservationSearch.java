package com.apress.spring6recipes.springintegration.myholiday;

import java.io.Serializable;
import java.time.LocalDate;

public record HotelReservationSearch (
				int roomsDesired, LocalDate start,
        LocalDate stop, double maxPrice) implements Serializable {
 }
