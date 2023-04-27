package com.apress.spring6recipes.court.feeds;

import com.rometools.rome.feed.atom.Content;
import com.rometools.rome.feed.atom.Entry;
import com.rometools.rome.feed.atom.Feed;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.feed.AbstractAtomFeedView;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AtomFeedView extends AbstractAtomFeedView {

	@Override
	protected void buildFeedMetadata(Map<String, Object> model, Feed feed,
																	 HttpServletRequest request) {
		feed.setId("tag:tennis.org");
		feed.setTitle("Grand Slam Tournaments");

		@SuppressWarnings({ "unchecked" })
		var tournaments = (List<TournamentContent>) model.get("feedContent");

		var updated = tournaments.stream()
						.map(TournamentContent::publicationDate).sorted().findFirst()
						.map(this::toDate).orElse(null);
		feed.setUpdated(updated);
	}

	@Override
	protected List<Entry> buildFeedEntries(Map<String, Object> model,
																				 HttpServletRequest request,
																				 HttpServletResponse response) {
		@SuppressWarnings({ "unchecked" })
		var tournaments = (List<TournamentContent>) model.get("feedContent");
		return tournaments.stream().map(this::toEntry).collect(Collectors.toList());
	}

	private Entry toEntry(TournamentContent tc) {
		var summary = new Content();
		summary.setValue(String.format("%s - %s", tc.name(), tc.link()));

		var entry = new Entry();
		var date = DateTimeFormatter.ISO_DATE.format(tc.publicationDate());
		entry.setId(String.format("tag:tennis.org,%s:%d", date, tc.id()));
		entry.setTitle(String.format("%s - Posted by %s", tc.name(), tc.author()));
		entry.setUpdated(toDate(tc.publicationDate()));
		entry.setSummary(summary);
		return entry;
	}

	private Date toDate(LocalDate in) {
		return Date.from(in.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
}
