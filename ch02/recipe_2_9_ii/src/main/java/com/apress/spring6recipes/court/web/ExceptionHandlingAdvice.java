package com.apress.spring6recipes.court.web;

import com.apress.spring6recipes.court.service.ReservationNotAvailableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by marten on 05-05-14.
 */
@ControllerAdvice
public class ExceptionHandlingAdvice {

	@ExceptionHandler(ReservationNotAvailableException.class)
	public String handle(ReservationNotAvailableException ex) {
		return "reservationNotAvailable";
	}

	@ExceptionHandler
	public String handleDefault(Exception e) {
		return "error";
	}

}
