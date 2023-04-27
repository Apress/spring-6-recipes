package com.apress.spring6recipes.court.service;

import com.apress.spring6recipes.court.domain.Member;

import java.util.Optional;

public interface MemberService {

	Iterable<Member> findAll();
	Optional<Member> findById(long id);
	Member save(Member member);

}
