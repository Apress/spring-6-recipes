package com.apress.spring6recipes.court.feeds;

import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Item;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RSSFeedView extends AbstractRssFeedView {

	@Override
	protected void buildFeedMetadata(Map<String, Object> model, Channel feed,
																	 HttpServletRequest request) {
		feed.setTitle("World Soccer Tournaments");
		feed.setDescription("FIFA World Soccer Tournament Calendar");
		feed.setLink("fifa.com");

		@SuppressWarnings({ "unchecked" })
		var tournaments = (List<TournamentContent>) model.get("feedContent");
		var lastBuildDate = tournaments.stream()
						.map(TournamentContent::publicationDate).sorted().findFirst()
						.map(this::toDate).orElse(null);
		feed.setLastBuildDate(lastBuildDate);
	}

	@Override
	protected List<Item> buildFeedItems(Map<String, Object> model,
																			HttpServletRequest request,
																			HttpServletResponse response) {
		@SuppressWarnings({ "unchecked" })
		var tournamentList = (List<TournamentContent>) model.get("feedContent");
		return tournamentList.stream().map(this::toItem).collect(Collectors.toList());
	}

	private Item toItem(TournamentContent tc) {
		var item = new Item();
		item.setAuthor(tc.author());
		item.setTitle(String.format("%s - Posted by %s", tc.name(), tc.author()));
		item.setPubDate(toDate(tc.publicationDate()));
		item.setLink(tc.link());
		return item;
	}

	private Date toDate(LocalDate in) {
		return Date.from(in.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
}
