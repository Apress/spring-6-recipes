package com.apress.spring6recipes.court.web;

import com.apress.spring6recipes.court.domain.Members;
import com.apress.spring6recipes.court.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestMemberController {

	private final MemberService memberService;

	public RestMemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/members")
	public String getRestMembers(Model model) {
		var members = new Members();
		members.addMembers(memberService.findAll());
		model.addAttribute("members", members);
		return "membertemplate";
	}
}
