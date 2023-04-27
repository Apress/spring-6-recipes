package com.apress.spring6recipes.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@Testcontainers
@ContextConfiguration(classes = BankConfiguration.class)
@Transactional
@ActiveProfiles("jdbc")
@Sql(scripts = { "classpath:/bank.sql" })
class AccountServiceContextTests {

	@Container
	private static final PostgreSQLContainer<?> POSTGRES =
					new PostgreSQLContainer<>("postgres:14.5");

	private static final String TEST_ACCOUNT_NO = "1234";

	@Autowired
	private AccountService accountService;

	@DynamicPropertySource
	static void registerPgProperties(DynamicPropertyRegistry registry) {
	  registry.add("jdbc.driver",   POSTGRES::getDriverClassName);
		registry.add("jdbc.url",      POSTGRES::getJdbcUrl);
		registry.add("jdbc.username", POSTGRES::getUsername);
		registry.add("jdbc.password", POSTGRES::getPassword);
	}

	@BeforeEach
	public void init() {
		accountService.createAccount(TEST_ACCOUNT_NO);
		accountService.deposit(TEST_ACCOUNT_NO, 100);
	}

	@Test
	void deposit() {
		accountService.deposit(TEST_ACCOUNT_NO, 50);
		assertEquals(150, accountService.getBalance(TEST_ACCOUNT_NO), 0);
	}

	@Test
	void withDraw() {
		accountService.withdraw(TEST_ACCOUNT_NO, 50);
		assertEquals(50, accountService.getBalance(TEST_ACCOUNT_NO), 0);
	}
}