package com.apress.spring6recipes.court.web;

public class ReservationWebException extends RuntimeException {

	public static final long serialVersionUID = 1L;

	public ReservationWebException(String message, Throwable cause) {
		super(message, cause);
	}
}