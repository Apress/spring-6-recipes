package com.apress.spring6recipes.court.web;

import com.apress.spring6recipes.court.config.CourtConfiguration;
import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Set;

public class CourtServletContainerInitializer implements ServletContainerInitializer {

	public static final String MSG = "Starting Court Web Application";

	@Override
	public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {

		ctx.log(MSG);

		var applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(CourtConfiguration.class);

		var dispatcherServlet = new DispatcherServlet(applicationContext);

		var courtRegistration = ctx.addServlet("court", dispatcherServlet);
		courtRegistration.addMapping("/");
		courtRegistration.setLoadOnStartup(1);
	}
}
