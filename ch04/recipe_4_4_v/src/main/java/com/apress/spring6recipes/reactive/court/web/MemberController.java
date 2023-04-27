package com.apress.spring6recipes.reactive.court.web;

import com.apress.spring6recipes.reactive.court.domain.Member;
import com.apress.spring6recipes.reactive.court.domain.SportType;
import com.apress.spring6recipes.reactive.court.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/members")
public class MemberController {

	private final MemberService memberService;
	private final SportTypeRepository sportTypeRepository;

	public MemberController(MemberService memberService, SportTypeRepository sportTypeRepository) {
		this.memberService = memberService;
		this.sportTypeRepository = sportTypeRepository;
	}

	@ModelAttribute("sportTypes")
	public Flux<SportType> sportTypes() {
		return sportTypeRepository.findAll();
	}

	@GetMapping
	public Mono<String> add(Model model) {
		model.addAttribute("member", new Member(null, null, null, null));
		return Mono.just("member");
	}

	@GetMapping("/{id}")
	public Mono<String> add(@PathVariable("id") long id, Model model) {
		return memberService.findById(id)
						.defaultIfEmpty(new Member(null, null, null, null))
						.doOnNext( (member) -> model.addAttribute("member", member))
						.then(Mono.just("member"));
	}

	@PostMapping
	public Mono<String> create(@Valid @ModelAttribute("member") Member member,
														 BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Mono.just("member");
		}
		return Mono.just(member)
						.map(memberService::save)
						.then(Mono.just("redirect:member-success"));
	}
}
