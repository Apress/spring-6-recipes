package com.apress.spring6recipes.court.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class WelcomeController {

	@GetMapping("/welcome")
	public String welcome(Model model) {
		model.addAttribute("today", LocalDate.now());
		return "/WEB-INF/jsp/welcome.jsp";
	}
}
