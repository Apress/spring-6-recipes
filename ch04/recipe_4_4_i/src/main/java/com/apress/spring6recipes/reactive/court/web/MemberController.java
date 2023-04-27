package com.apress.spring6recipes.reactive.court.web;

import com.apress.spring6recipes.reactive.court.domain.Member;
import com.apress.spring6recipes.reactive.court.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/members")
public class MemberController {

	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping
	public Mono<String> add(Model model) {
		model.addAttribute("member", new Member(null, null, null));
		return Mono.just("member");
	}

	@PostMapping
	public Mono<String> create(@ModelAttribute("member") Member member,
														 BindingResult bindingResult) {
		return Mono.just(member)
						.map(memberService::save)
						.then(Mono.just("redirect:member-success"));
	}
}
