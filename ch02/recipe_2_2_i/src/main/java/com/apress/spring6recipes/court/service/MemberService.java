package com.apress.spring6recipes.court.service;

import com.apress.spring6recipes.court.domain.Member;

import java.util.List;

public interface MemberService {

	void add(Member member);
	void remove(String memberName);
	List<Member> list();

}
