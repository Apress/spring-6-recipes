package com.apress.spring6recipes.reactive.court.web;

import com.apress.spring6recipes.reactive.court.domain.Member;
import com.apress.spring6recipes.reactive.court.service.MemberService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class MemberController {

	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	public Mono<ServerResponse> list(ServerRequest request) {
		return ServerResponse
						.ok()
						.body(memberService.findAll(), Member.class);
	}

	public Mono<ServerResponse> create(ServerRequest request) {
		var member = request.bodyToMono(Member.class)
												.flatMap(memberService::save);
		return ServerResponse.ok().body(member, Member.class);
	}
}
