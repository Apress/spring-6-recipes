package com.apress.spring6recipes.sequence.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
	includeFilters = {
			@ComponentScan.Filter(
				type = FilterType.REGEX,
				pattern = {
						"com.apress.spring6recipes.sequence.*Dao",
						"com.apress.spring6recipes.sequence.*Service"})},
	excludeFilters = {
			@ComponentScan.Filter(
				type = FilterType.ANNOTATION,
				classes = {org.springframework.stereotype.Controller.class})})
public class SequenceConfiguration { }