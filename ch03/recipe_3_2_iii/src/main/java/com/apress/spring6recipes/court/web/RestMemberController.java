package com.apress.spring6recipes.court.web;

import com.apress.spring6recipes.court.domain.Member;
import com.apress.spring6recipes.court.domain.Members;
import com.apress.spring6recipes.court.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestMemberController {

	private final MemberService memberService;

	public RestMemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/members")
	@ResponseBody
	public Members getRestMembers() {
		var members = new Members();
		members.addMembers(memberService.findAll());
		return members;
	}

	@GetMapping("/members/{memberid}")
	@ResponseBody
	public ResponseEntity<Member> getMember(@PathVariable("memberid") long memberID) {
		return memberService.findById(memberID)
						.map(ResponseEntity::ok).orElse(null);
	}
}
