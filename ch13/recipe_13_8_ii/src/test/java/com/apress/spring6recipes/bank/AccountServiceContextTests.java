package com.apress.spring6recipes.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@ContextConfiguration(classes = BankConfiguration.class)
@ActiveProfiles("jdbc")
@Sql(scripts = { "classpath:/bank.sql" })
public class AccountServiceContextTests extends AbstractTransactionalTestNGSpringContextTests {

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

	@BeforeSuite
	public static void start() {
		POSTGRES.start();
	}

	@AfterSuite
	public static void stop() {
		POSTGRES.stop();
	}

	@BeforeMethod
	public void init() {
		accountService.createAccount(TEST_ACCOUNT_NO);
		accountService.deposit(TEST_ACCOUNT_NO, 100);
	}

	@Test
	public void deposit() {
		accountService.deposit(TEST_ACCOUNT_NO, 50);
		assertEquals(accountService.getBalance(TEST_ACCOUNT_NO), 150, 0);
	}

	@Test
	public void withDraw() {
		accountService.withdraw(TEST_ACCOUNT_NO, 50);
		assertEquals(accountService.getBalance(TEST_ACCOUNT_NO), 50, 0);
	}
}
