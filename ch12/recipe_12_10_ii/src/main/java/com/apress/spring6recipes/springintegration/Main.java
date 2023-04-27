package com.apress.spring6recipes.springintegration;

import java.time.LocalDate;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.spring6recipes.springintegration.myholiday.HotelReservationSearch;
import com.apress.spring6recipes.springintegration.myholiday.VacationService;

public class Main {

	public static void main(String[] args) throws Throwable {
		try (var serverCtx = new AnnotationConfigApplicationContext(ServerIntegrationContext.class);
					var clientCtx = new AnnotationConfigApplicationContext(ClientIntegrationConfig.class)) {

			var vacationService = clientCtx.getBean(VacationService.class);

			var now = LocalDate.now();
			var start = now.plusDays(1);
			var stop = now.plusDays(8);
			var hotelReservationSearch = new HotelReservationSearch(2, start, stop, 200f);
			var results = vacationService.findHotels(hotelReservationSearch);

			System.out.printf("Found %s results.%n", results.size());
			results.forEach(r -> System.out.printf("\t%s%n", r));
		}
	}
}
