package com.example.transactiontest.application.member.repository;

import com.example.transactiontest.application.member.domain.Address;
import com.example.transactiontest.application.member.domain.Member;
import com.example.transactiontest.application.member.exception.NotFoundMemberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static com.example.transactiontest.application.member.stub.StubMember.address;
import static com.example.transactiontest.application.member.stub.StubMember.makeMember;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Member 클래스")
class MemberRepositoryTest {

	@Autowired
	private MemberRepository memberRepository;

	@BeforeEach
	void setUp() {
		memberRepository.saveAll(
				List.of(makeMember(address("서울시", "논현로", "11길"))
						, makeMember(address("서울시", "강남구", "테헤란로 111"))
						, makeMember(address("서울시", "양재동", "315-5번지"))
						, makeMember(address("서울시", "역삼동", "강남역"))
						, makeMember(address("서울시", "송파구", "올림픽로 3000"))));
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
		@CsvSource(value = "1,서울시,논현로,11길,updatedMemberName")
		@ParameterizedTest(name = "id:{0} 로 조회")
		void testCase1(long id, String city, String street, String zipCode, String updatedName) {
			Member member = memberRepository.findById(id)
					.orElseThrow(() -> new NotFoundMemberException("존재하지 않는 사용자입니다. : {}", id));

			Address address = address(city, street, zipCode);

			member.update(updatedName, address);
			memberRepository.flush();

			assertThat(member.getName()).isEqualTo(updatedName);
			assertThat(member.getAddress()).isEqualTo(address);
		}
	}
}