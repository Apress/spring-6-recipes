package com.apress.spring6recipes.reactive.court.service;

import com.apress.spring6recipes.reactive.court.domain.Member;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
class InMemoryMemberService implements MemberService {

	private final AtomicLong sequence = new AtomicLong(1);
	private final Map<Long, Member> members = new ConcurrentHashMap<>();

	@PostConstruct
	public void init() {
		Flux.just(
				new Member("Marten Deinum", "00-31-1234567890", "marten@deinum.biz"),
				new Member("John Doe", "1-800-800-800", "john@doe.com"),
				new Member("Jane Doe", "1-801-802-803", "jane@doe.com"))
					.flatMap(this::save)
					.subscribe();
	}

	@Override
	public Flux<Member> findAll() {
		return Flux.fromIterable(members.values());
	}

	@Override
	public Mono<Member> findById(long id) {
		return Mono.justOrEmpty(members.get(id));
	}

	@Override
	public Mono<Member> save(Member member) {
		var id = sequence.getAndIncrement();
		this.members.put(id, member);
		return Mono.just(member);
	}
}
