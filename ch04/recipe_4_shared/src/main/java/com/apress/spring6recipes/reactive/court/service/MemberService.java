package com.apress.spring6recipes.reactive.court.service;

import com.apress.spring6recipes.reactive.court.domain.Member;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MemberService {

	Flux<Member> findAll();
	Mono<Member> findById(long id);
	Mono<Member> save(Member member);

}
