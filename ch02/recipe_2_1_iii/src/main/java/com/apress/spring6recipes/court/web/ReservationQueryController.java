package com.apress.spring6recipes.court.web;

import com.apress.spring6recipes.court.domain.Reservation;
import com.apress.spring6recipes.court.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reservationQuery")
public class ReservationQueryController {

	private final ReservationService reservationService;

	public ReservationQueryController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping
	public void setupForm() {}

	@PostMapping
	public String sumbitForm(@RequestParam("courtName") String courtName, Model model) {
		var reservations = java.util.Collections.<Reservation>emptyList();
		if (courtName != null) {
			reservations = reservationService.query(courtName);
		}
		model.addAttribute("reservations", reservations);
		return "/WEB-INF/jsp/reservationQuery.jsp";
	}
}