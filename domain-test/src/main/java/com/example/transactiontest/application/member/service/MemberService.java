package com.example.transactiontest.application.member.service;

import com.example.transactiontest.application.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements MemberCommandService, MemberQueryService {

	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
}
