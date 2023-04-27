package com.apress.spring6recipes.court.web;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class CourtExceptionHandlers extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
																																HttpHeaders headers, HttpStatusCode status,
																																WebRequest request) {
		var errors = ex.getAllErrors().stream()
						.collect(Collectors.toMap(this::getKey, this::resolveMessage));
		ex.getBody().setProperty("errors", errors);
		return super.handleExceptionInternal(ex, null, headers, status, request);
	}

	private String getKey(ObjectError error) {
		return (error instanceof FieldError fe) ? fe.getCode() : error.getObjectName();
	}

	private String resolveMessage(ObjectError error) {
		return getMessageSource() != null
						? getMessageSource().getMessage(error, LocaleContextHolder.getLocale())
						: error.getDefaultMessage();
	}
}
