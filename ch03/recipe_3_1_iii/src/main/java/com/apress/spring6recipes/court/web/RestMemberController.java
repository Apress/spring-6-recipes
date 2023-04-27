package com.apress.spring6recipes.court.web;

import com.apress.spring6recipes.court.domain.Members;
import com.apress.spring6recipes.court.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestMemberController {

	private final MemberService memberService;

	public RestMemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/members")
	public Members getRestMembers() {
		var members = new Members();
		members.addMembers(memberService.findAll());
		return members;
	}
}
