package com.apress.spring6recipes.course.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.apress.spring6recipes.course.Course;
import com.apress.spring6recipes.course.CourseDao;
import com.apress.spring6recipes.course.hibernate.HibernateCourseDao;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
public class CourseConfiguration {

	@Bean
	public CourseDao courseDao(SessionFactory sessionFactory) {
		return new HibernateCourseDao(sessionFactory);
	}

	@Bean
	public DataSource dataSource() {
		var dataSource = new HikariDataSource();
		dataSource.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
		dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/course");
		dataSource.setUsername("postgres");
		dataSource.setPassword("password");
		dataSource.setMinimumIdle(2);
		dataSource.setMaximumPoolSize(5);
		return dataSource;
	}

	@Bean
	public SessionFactory sessionFactory(DataSource dataSource) {
		return new LocalSessionFactoryBuilder(dataSource)
						.addAnnotatedClasses(Course.class)
						.addProperties(hibernateProperties()).buildSessionFactory();
	}

	private Properties hibernateProperties() {
		var properties = new Properties();
		properties.setProperty(AvailableSettings.SHOW_SQL, String.valueOf(true));
		properties.setProperty(AvailableSettings.HBM2DDL_AUTO, "update");
		return properties;
	}

	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sf) {
		return new HibernateTransactionManager(sf);
	}
}
