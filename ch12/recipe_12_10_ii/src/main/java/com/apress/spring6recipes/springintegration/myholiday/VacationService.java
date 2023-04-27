package com.apress.spring6recipes.springintegration.myholiday;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import java.util.List;


@MessagingGateway
public interface VacationService {

    @Gateway
    List<HotelReservation> findHotels(HotelReservationSearch search);
}
