package com.apress.spring6recipes.bank.web;

import com.apress.spring6recipes.bank.AccountService;
import org.mockito.Mockito;
import org.springframework.ui.ModelMap;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class DepositControllerTests {

	private static final String TEST_ACCOUNT_NO = "1234";

	private static final double TEST_AMOUNT = 50;

	private AccountService accountService;

	private DepositController depositController;

	@BeforeMethod
	public void init() {
		accountService = Mockito.mock(AccountService.class);
		depositController = new DepositController(accountService);
	}

	@Test
	public void deposit() {
		// Setup
		Mockito.when(accountService.getBalance(TEST_ACCOUNT_NO)).thenReturn(150.0);
		ModelMap model = new ModelMap();

		// Execute
		String viewName = depositController.deposit(TEST_ACCOUNT_NO, TEST_AMOUNT, model);

		assertEquals(viewName, "success");
		assertEquals(model.get("accountNo"), TEST_ACCOUNT_NO);
		assertEquals(model.get("balance"), 150.0);
	}

}
