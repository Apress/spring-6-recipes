package com.apress.spring6recipes.court.config;

import com.apress.spring6recipes.court.web.view.ExcelReservationSummary;
import com.apress.spring6recipes.court.web.view.PdfReservationSummary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;

@Configuration
public class ViewResolverConfiguration implements WebMvcConfigurer {

	public static final MediaType APPLICATION_EXCEL = MediaType.valueOf("application/vnd.ms-excel");

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.mediaType("html", MediaType.TEXT_HTML);
		configurer.mediaType("pdf", MediaType.APPLICATION_PDF);
		configurer.mediaType("xls", APPLICATION_EXCEL);
		configurer.favorPathExtension(true);
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.enableContentNegotiation();
		registry.jsp("/WEB-INF/jsp/", ".jsp");
		registry.viewResolver(new BeanNameViewResolver());
	}

	@Bean(name = "reservationSummary.pdf")
	public PdfReservationSummary pdfReservationSummaryView() {
		return new PdfReservationSummary();
	}

	@Bean(name = "reservationSummary.xls")
	public ExcelReservationSummary excelReservationSummaryView() {
		return new ExcelReservationSummary();
	}
}
