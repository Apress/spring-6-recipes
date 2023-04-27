package com.apress.spring6recipes.court.web;

import com.apress.spring6recipes.court.service.ReservationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.time.LocalDate;

@Controller
@RequestMapping("/reservationSummary*")
public class ReservationSummaryController {

	private final ReservationService reservationService;

	public ReservationSummaryController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String generateSummary(
					@RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate selectedDate,
					Model model) {
		var reservations = reservationService.findByDate(selectedDate);
		model.addAttribute("reservations", reservations);
		return "reservationSummary";
	}

	@ExceptionHandler
	public void handle(ServletRequestBindingException ex) {
		if (ex.getRootCause() instanceof ParseException) {
			throw new ReservationWebException("Invalid date format for reservation summary", ex);
		}
	}
}
