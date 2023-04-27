package com.apress.spring6recipes.reactive.court.web;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Flux;

@ControllerAdvice
public class GlobalErrorHandler {

	@ExceptionHandler(WebExchangeBindException.class)
	@ResponseBody
	public Flux<ErrorMessage> handleValidationErrors(WebExchangeBindException ex) {
		return Flux.fromIterable(ex.getFieldErrors())
						.map(this::toErrorMessage);
	}

	private ErrorMessage toErrorMessage(FieldError fe) {
		return new ErrorMessage(fe.getField(), fe.getDefaultMessage());
	}

	record ErrorMessage(String field, String message) { }

}
