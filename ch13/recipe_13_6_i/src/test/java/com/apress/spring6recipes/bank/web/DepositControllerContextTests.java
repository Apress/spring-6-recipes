package com.apress.spring6recipes.bank.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.apress.spring6recipes.bank.Account;
import com.apress.spring6recipes.bank.AccountDao;
import com.apress.spring6recipes.bank.BankConfiguration;
import com.apress.spring6recipes.bank.web.config.BankWebConfiguration;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
				BankWebConfiguration.class,
				BankConfiguration.class })
@WebAppConfiguration
class DepositControllerContextTests {

	private static final String ACCOUNT_PARAM = "accountNo";
	private static final String AMOUNT_PARAM = "amount";
	private static final String TEST_ACCOUNT_NO = "1234";
	private static final String TEST_AMOUNT = "50.0";

	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private AccountDao accountDao;
	private MockMvc mockMvc;

	@BeforeEach
	public void init() {
		accountDao.createAccount(new Account(TEST_ACCOUNT_NO, 100));
		mockMvc = MockMvcBuilders
						.webAppContextSetup(webApplicationContext)
						.build();
	}

	@Test
	void deposit() throws Exception {
		mockMvc.perform(
						post("/deposit")
										.param(ACCOUNT_PARAM, TEST_ACCOUNT_NO)
										.param(AMOUNT_PARAM, TEST_AMOUNT))
				.andExpect(forwardedUrl("/WEB-INF/views/success.jsp"))
				.andExpect(status().isOk());
	}
}