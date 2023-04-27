package com.apress.spring6recipes.court.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for the Court reservation application. Enables component scannig so
 * that all services and controllers will be detected (as long as they are part of the
 * {@code com.apress.springrecipes.court} package (or in a sub-package).
 *
 * @author Marten Deinum
 */
@Configuration
@ComponentScan("com.apress.spring6recipes.court")
@EnableWebMvc
public class CourtConfiguration implements WebMvcConfigurer {
}
