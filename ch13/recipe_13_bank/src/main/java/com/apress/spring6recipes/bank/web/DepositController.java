package com.apress.spring6recipes.bank.web;

import com.apress.spring6recipes.bank.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DepositController {

	private final AccountService accountService;

	public DepositController(AccountService accountService) {
		this.accountService = accountService;
	}

	@PostMapping("/deposit")
	public String deposit(@RequestParam("accountNo") String accountNo,
												@RequestParam("amount") double amount,
												Model model) {
		accountService.deposit(accountNo, amount);
		model.addAttribute("accountNo", accountNo);
		model.addAttribute("balance", accountService.getBalance(accountNo));
		return "success";
	}
}