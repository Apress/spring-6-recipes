package com.apress.spring6recipes.course.config;

import jakarta.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

import com.apress.spring6recipes.course.CourseDao;
import com.apress.spring6recipes.course.jpa.JpaCourseDao;

@Configuration
public class CourseConfiguration {

	@Bean
	public CourseDao courseDao(EntityManagerFactory entityManagerFactory) {
		return new JpaCourseDao(entityManagerFactory);
	}

	@Bean
	public LocalEntityManagerFactoryBean entityManagerFactory() {
		var emf = new LocalEntityManagerFactoryBean();
		emf.setPersistenceUnitName("course");
		return emf;
	}
}
