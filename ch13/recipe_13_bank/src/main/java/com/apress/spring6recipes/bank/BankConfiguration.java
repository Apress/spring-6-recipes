package com.apress.spring6recipes.bank;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(value = "com.apress.spring6recipes.bank",
				excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)})
public class BankConfiguration {

	@Bean
	public static PropertySourcesPlaceholderConfigurer pspc() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Configuration
	@Profile("jdbc")
	@PropertySource("classpath:/application.properties")
	public static class JdbcBankConfiguration {

		private final Environment env;

		public JdbcBankConfiguration(Environment env) {
			this.env = env;
		}

		@Bean
		public DataSource dataSource() {
			var dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driver"));
			dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
			dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
			dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
			return dataSource;
		}

		@Bean
		public DataSourceTransactionManager transactionManager(DataSource dataSource) {
			return new DataSourceTransactionManager(dataSource);
		}

		@Bean
		public JdbcTemplate jdbcTemplate(DataSource dataSource) {
			return new JdbcTemplate(dataSource);
		}

		@Bean
		public AccountDao accountDao(JdbcTemplate jdbcTemplate) {
			return new JdbcAccountDao(jdbcTemplate);
		}
	}

	@Configuration
	@Profile("!jdbc")
	public static class InMemoryBankConfiguration {

		@Bean
		public AccountDao accountDao() {
			return new InMemoryAccountDao();
		}
	}
}
