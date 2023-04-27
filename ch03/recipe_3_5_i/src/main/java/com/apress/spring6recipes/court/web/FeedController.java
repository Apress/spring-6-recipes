package com.apress.spring6recipes.court.web;

import com.apress.spring6recipes.court.feeds.TournamentContent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class FeedController {

	@GetMapping("/atomfeed")
	public String getAtomFeed(Model model) {
		var date = LocalDate.now();
		var tournaments = List.of(
				TournamentContent.of("ATP", date, "Australian Open", "www.australianopen.com"),
				TournamentContent.of("ATP", date, "Roland Garros", "www.rolandgarros.com"),
				TournamentContent.of("ATP", date, "Wimbledon", "www.wimbledon.org"),
				TournamentContent.of("ATP", date, "US Open", "www.usopen.org"));
		model.addAttribute("feedContent", tournaments);
		return "atomfeedtemplate";
	}

	@GetMapping("/rssfeed")
	public String getRSSFeed(Model model) {
		prepareModel(model);
		return "rssfeedtemplate";
	}

	@GetMapping("/jsontournament")
	public String getJSON(Model model) {
		prepareModel(model);
		return "jsontournamenttemplate";
	}

	private void prepareModel(Model model) {
		var date = LocalDate.now();
		var tournaments = List.of(
				TournamentContent.of("FIFA", date, "World Cup", "www.fifa.com/worldcup/"),
				TournamentContent.of("FIFA", date, "U-20 World Cup", "www.fifa.com/u20worldcup/"),
				TournamentContent.of("FIFA", date, "U-17 World Cup", "www.fifa.com/u17worldcup/"),
				TournamentContent.of("FIFA", date, "Confederations Cup", "www.fifa.com/confederationscup/"));
		model.addAttribute("feedContent", tournaments);
	}
}
