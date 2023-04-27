package com.apress.spring6recipes.court.service;

import com.apress.spring6recipes.court.domain.Member;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
class InMemoryMemberService implements MemberService {

	private Map<String, Member> members = new HashMap<>();

	public void add(Member member) {
		members.put(member.getName(), member);
	}

	public void remove(String memberName) {
		members.remove(memberName);
	}

	public List<Member> list() {
		return new ArrayList<>(members.values());
	}

}
