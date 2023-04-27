package com.apress.spring6recipes.sequence;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatePrefixGenerator implements PrefixGenerator {

	private DateTimeFormatter formatter;

	public void setPattern(String pattern) {
		this.formatter = DateTimeFormatter.ofPattern(pattern);
	}

	public String getPrefix() {
		return formatter.format(LocalDateTime.now());
	}

}
