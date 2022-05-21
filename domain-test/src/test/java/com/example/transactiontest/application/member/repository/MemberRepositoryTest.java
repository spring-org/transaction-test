package com.example.transactiontest.application.member.repository;

import com.example.transactiontest.application.member.domain.Address;
import com.example.transactiontest.application.member.domain.Member;
import com.example.transactiontest.application.member.exception.NotFoundMemberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Member 클래스")
class MemberRepositoryTest {

	@Autowired
	private MemberRepository memberRepository;

	@BeforeEach
	void setUp() {
		memberRepository.saveAll(
				List.of(makeMemberEntity(1L)
						, makeMemberEntity(2L)
						, makeMemberEntity(3L)
						, makeMemberEntity(4L)
						, makeMemberEntity(5L)));
	}

	@Nested
	@DisplayName("Query 관련")
	class QueryMemberTest {

		@DisplayName("고객 조회 테스트")
		@Test
		void testCase1() {
			boolean exists = memberRepository.existsById(1L);
			assertThat(exists).isTrue();
		}

		@DisplayName("고객 리스트 페이징 조회 테스트")
		@Test
		void testCase2() {
			// 5건 데이터 1페이지 2개씩
			PageRequest pageRequest = PageRequest.of(1, 2);
			Page<Member> memberPage = memberRepository.findAll(pageRequest);

			assertThat(memberPage.getTotalPages()).isEqualTo(3);
			assertThat(memberPage.getTotalElements()).isEqualTo(5);
		}
	}

	@Nested
	@DisplayName("Command 관련")
	class CommandMemberTest {

		@DisplayName("고객 정보 수정 테스트")
		@Test
		void testCase1() {
			long id = 1L;
			Member member = memberRepository.findById(id)
					.orElseThrow(() -> new NotFoundMemberException("존재하지 않는 사용자입니다. : {}", id));
			member.update("updatedMemberName", makeAddress());
			memberRepository.flush();

			assertThat(member.getName()).isEqualTo("updatedMemberName");
			assertThat(member.getAddress()).isEqualTo(makeAddress());
		}
	}

	private Address makeAddress() {
		return new Address.Builder("서울시", "논현로", "11길")
				.build();
	}

	private Member makeMemberEntity(long id) {
		return new Member.Builder(id).build();
	}
}