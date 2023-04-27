package com.apress.spring6recipes.court.config;

import com.apress.spring6recipes.court.web.view.ExcelReservationSummary;
import com.apress.spring6recipes.court.web.view.PdfReservationSummary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;

import java.util.Map;

@Configuration
public class ViewResolverConfiguration implements WebMvcConfigurer {

	public static final MediaType APPLICATION_EXCEL = MediaType.valueOf("application/vnd.ms-excel");

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/reservationSuccess").setViewName("reservationSuccess");
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		var mediatypes = Map.of(
						"html", MediaType.TEXT_HTML,
						"pdf", MediaType.APPLICATION_PDF,
						"xls", APPLICATION_EXCEL);
		configurer.mediaTypes(mediatypes);
	}

	@Bean(name = "reservationSummary.pdf")
	public PdfReservationSummary pdfReservationSummaryView() {
		return new PdfReservationSummary();
	}

	@Bean(name = "reservationSummary.xls")
	public ExcelReservationSummary excelReservationSummaryView() {
		return new ExcelReservationSummary();
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/jsp/", ".jsp");
		registry.viewResolver(new BeanNameViewResolver());
	}
}
