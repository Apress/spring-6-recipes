package com.apress.spring6recipes.court.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ViewResolverConfiguration implements WebMvcConfigurer {

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.mediaType("html", MediaType.TEXT_HTML);
		configurer.mediaType("xls", MediaType.valueOf("application/vnd.ms-excel"));
		configurer.mediaType("pdf", MediaType.APPLICATION_PDF);
		configurer.mediaType("xml", MediaType.APPLICATION_XML);
		configurer.mediaType("json", MediaType.APPLICATION_JSON);
		configurer.favorPathExtension(true);
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.enableContentNegotiation();
		registry.jsp("/WEB-INF/jsp/", ".jsp");
	}
}
