package com.apress.spring6recipes.caching.config;

import com.apress.spring6recipes.caching.CustomerRepository;
import com.apress.spring6recipes.caching.JdbcCustomerRepository;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.transaction.TransactionAwareCacheManagerProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.time.Duration;

@Configuration
@EnableCaching
@EnableTransactionManagement
public class CustomerConfiguration {

	@Bean
	public CacheManager cacheManager() {
		var caffeine = Caffeine.newBuilder()
						.maximumSize(1000)
						.expireAfterWrite(Duration.ofMinutes(5));
		var cacheManager = new CaffeineCacheManager();
		cacheManager.setCaffeine(caffeine);
		return new TransactionAwareCacheManagerProxy(cacheManager);
	}

	@Bean
	public CustomerRepository customerRepository(JdbcTemplate jdbc) {
		return new JdbcCustomerRepository(jdbc);
	}

	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
						.setType(EmbeddedDatabaseType.H2)
						.setName("customers")
						.addScript("classpath:/schema.sql").build();
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource ds) {
		return new JdbcTemplate(ds);
	}
}
