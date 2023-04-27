package com.apress.spring6recipes.course.config;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

import com.apress.spring6recipes.course.Course;
import com.apress.spring6recipes.course.CourseDao;
import com.apress.spring6recipes.course.hibernate.HibernateCourseDao;

@Configuration
public class CourseConfiguration {

	@Bean
	public CourseDao courseDao(SessionFactory sessionFactory) {
		return new HibernateCourseDao(sessionFactory);
	}

	@Bean
	public SessionFactory sessionFactory() {
		return new LocalSessionFactoryBuilder(null)
						.addAnnotatedClasses(Course.class)
						.addProperties(hibernateProperties())
						.buildSessionFactory();
	}

	private Properties hibernateProperties() {
		var url = "jdbc:postgresql://localhost:5432/course";
		var properties = new Properties();
		properties.setProperty(AvailableSettings.URL, url);
		properties.setProperty(AvailableSettings.USER, "postgres");
		properties.setProperty(AvailableSettings.PASS, "password");
		properties.setProperty(AvailableSettings.SHOW_SQL, String.valueOf(true));
		properties.setProperty(AvailableSettings.HBM2DDL_AUTO, "update");
		return properties;
	}
}
