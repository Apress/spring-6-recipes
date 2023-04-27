package com.apress.spring6recipes.court.service;

import com.apress.spring6recipes.court.domain.Member;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
class InMemoryMemberService implements MemberService {

	private final AtomicLong sequence = new AtomicLong(1);
	private final Map<Long, Member> members = new HashMap<>();

	@PostConstruct
	public void init() {
		var members = List.of(
						new Member("Marten Deinum", "00-31-1234567890", "marten@deinum.biz"),
						new Member("John Doe", "1-800-800-800", "john@doe.com"),
						new Member("Jane Doe", "1-801-802-803", "jane@doe.com"));

		members.forEach( m -> this.members.put(sequence.getAndIncrement(), m));
	}

	@Override
	public Iterable<Member> findAll() {
		return members.values();
	}

	@Override
	public Optional<Member> findById(long id) {
		return Optional.ofNullable(members.get(id));
	}

	@Override
	public Member save(Member member) {
		var id = sequence.getAndIncrement();
		this.members.put(id, member);
		return member;
	}
}
