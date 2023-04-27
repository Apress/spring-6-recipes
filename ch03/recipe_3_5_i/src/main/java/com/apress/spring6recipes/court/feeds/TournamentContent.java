package com.apress.spring6recipes.court.feeds;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public record TournamentContent(String author, LocalDate publicationDate,
																String name, String link, int id) {

	private static final AtomicInteger idCounter = new AtomicInteger();

	public static TournamentContent of(String author, LocalDate date, String name, String link) {
		return new TournamentContent(author, date, name, link, idCounter.incrementAndGet());
	}
}
