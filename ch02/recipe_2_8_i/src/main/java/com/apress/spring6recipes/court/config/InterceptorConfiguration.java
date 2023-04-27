package com.apress.spring6recipes.court.config;

import com.apress.spring6recipes.court.web.ExtensionInterceptor;
import com.apress.spring6recipes.court.web.MeasurementInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(measurementInterceptor());
		registry.addInterceptor(summaryReportInterceptor()).addPathPatterns("/reservationSummary*");
	}

	@Bean
	public MeasurementInterceptor measurementInterceptor() {
		return new MeasurementInterceptor();
	}

	@Bean
	public ExtensionInterceptor summaryReportInterceptor() {
		return new ExtensionInterceptor(cnm);
	}
}
