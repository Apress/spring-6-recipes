package com.apress.spring6recipes.springintegration.myholiday;

import com.apress.spring6recipes.utils.Utils;
import jakarta.annotation.PostConstruct;
import org.springframework.integration.annotation.ServiceActivator;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class VacationServiceImpl implements VacationService {

    private List<HotelReservation> hotelReservations;

    @PostConstruct
    public void afterPropertiesSet() {
        hotelReservations = List.of(
                new HotelReservation("Bilton", 243.200F),
                new HotelReservation("East Western", 75.0F),
                new HotelReservation("Thairfield Inn", 70F),
                new HotelReservation("Park In The Inn", 200.00F));
    }

    @ServiceActivator
    public List<HotelReservation> findHotels(HotelReservationSearch search) {
        Utils.sleep(1, TimeUnit.SECONDS);
        return this.hotelReservations.stream()
				        .filter((hr) -> hr.price() <= search.maxPrice())
				        .toList();
    }
}
