package com.apress.spring6recipes.sequence;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * {@code PrefixGenerator} implementation which will create a prefix based on the current
 * date and time.
 *
 * @author Marten Deinum
 */
public class DatePrefixGenerator implements PrefixGenerator {

	private final DateTimeFormatter formatter;

	public DatePrefixGenerator(String pattern) {
		this.formatter = DateTimeFormatter.ofPattern(pattern);
	}

	public String getPrefix() {
		return formatter.format(LocalDateTime.now());
	}
}
