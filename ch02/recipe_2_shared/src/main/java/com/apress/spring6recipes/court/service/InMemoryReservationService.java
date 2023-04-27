package com.apress.spring6recipes.court.service;

import com.apress.spring6recipes.court.domain.PeriodicReservation;
import com.apress.spring6recipes.court.domain.Player;
import com.apress.spring6recipes.court.domain.Reservation;
import com.apress.spring6recipes.court.domain.SportType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
class InMemoryReservationService implements ReservationService {

	private static final SportType TENNIS = new SportType(1, "Tennis");
	private static final SportType SOCCER = new SportType(2, "Soccer");

	private final List<Reservation> reservations = Collections.synchronizedList(new ArrayList<>());

	public InMemoryReservationService() {

		var roger = new Player("Roger");
		var james = new Player("James");
		var date = LocalDate.of(2022, 10, 18);
		reservations.add(new Reservation("Tennis #1", date, 16, roger, TENNIS));
		reservations.add(new Reservation("Tennis #2", date, 20, james, TENNIS));
	}

	@Override
	public List<Reservation> query(String courtName) {
		return this.reservations.stream()
						.filter( (r) -> StringUtils.startsWithIgnoreCase(r.getCourtName(), courtName))
						.collect(Collectors.toList());
	}

	@Override
	public void make(Reservation res) throws ReservationNotAvailableException {
		long cnt = reservations.stream()
						.filter((r) -> Objects.equals(r.getCourtName(), res.getCourtName()))
						.filter((r) -> Objects.equals(r.getDate(), res.getDate()))
						.filter((r) -> r.getHour() == res.getHour()).count();

		if (cnt > 0) {
			throw new ReservationNotAvailableException(res.getCourtName(), res.getDate(),
							res.getHour());
		} else {
			reservations.add(res);
		}
	}

	@Override
	public List<SportType> getAllSportTypes() {
		return List.of(TENNIS, SOCCER);
	}

	@Override
	public SportType getSportType(int sportTypeId) {
		return switch (sportTypeId) {
			case 1 -> TENNIS;
			case 2 -> SOCCER;
			default -> null;
		};
	}

	@Override
	public List<Reservation> findByDate(LocalDate summaryDate) {
		return reservations.stream()
						.filter( (r) -> Objects.equals(r.getDate(), summaryDate))
						.toList();
	}

	@Override
	public void makePeriodic(PeriodicReservation periodicReservation) throws ReservationNotAvailableException {

		var fromDate = periodicReservation.getFromDate();
		while (fromDate.isBefore(periodicReservation.getToDate())) {
			var reservation = new Reservation();
			reservation.setCourtName(periodicReservation.getCourtName());
			reservation.setDate(fromDate);
			reservation.setHour(periodicReservation.getHour());
			reservation.setPlayer(periodicReservation.getPlayer());
			make(reservation);
			fromDate = fromDate.plusDays(periodicReservation.getPeriod());
		}
	}
}
