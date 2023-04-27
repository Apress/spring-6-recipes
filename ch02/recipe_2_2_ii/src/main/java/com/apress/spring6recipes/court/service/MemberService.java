package com.apress.spring6recipes.court.service;

import com.apress.spring6recipes.court.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {

	void add(Member member);
	void remove(String memberName);
	Optional<Member> find(String memberName);
	List<Member> list();

}
