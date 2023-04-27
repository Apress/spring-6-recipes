package com.apress.spring6recipes.reactive.court.web;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.server.MissingRequestValueException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebInputException;

import reactor.core.publisher.Mono;

@ControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

	@Override
	protected Mono<ResponseEntity<Object>> handleWebExchangeBindException(
					WebExchangeBindException ex, HttpHeaders headers,
					HttpStatusCode status, ServerWebExchange exchange) {
		var locale = exchange.getLocaleContext().getLocale();
		var errors = ex.resolveErrorMessages(getMessageSource(), locale);
		ex.getBody().setProperty("errors", errors.values());
		return super.handleExceptionInternal(ex, null, headers, status, exchange);
	}
}
