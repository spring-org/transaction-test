package com.example.transactiontest.application.member.repository;

import com.example.transactiontest.application.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
