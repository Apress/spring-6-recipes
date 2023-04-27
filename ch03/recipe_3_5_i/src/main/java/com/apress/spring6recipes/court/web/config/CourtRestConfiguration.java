package com.apress.spring6recipes.court.web.config;

import com.apress.spring6recipes.court.feeds.AtomFeedView;
import com.apress.spring6recipes.court.feeds.RSSFeedView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.apress.spring6recipes.court")
public class CourtRestConfiguration {

	@Bean
	public AtomFeedView atomfeedtemplate() {
		return new AtomFeedView();
	}

	@Bean
	public RSSFeedView rssfeedtemplate() {
		return new RSSFeedView();
	}

	@Bean
	public MappingJackson2JsonView jsontournamenttemplate() {
		var view = new MappingJackson2JsonView();
		view.setPrettyPrint(true);
		view.setModelKey("feedContent");
		return view;
	}

	@Bean
	public BeanNameViewResolver viewResolver() {
		return new BeanNameViewResolver();
	}
}
