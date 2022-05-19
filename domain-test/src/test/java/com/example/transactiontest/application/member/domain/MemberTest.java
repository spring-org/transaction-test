package com.example.transactiontest.application.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("고객 클래스")
class MemberTest {

	@Nested
	@DisplayName("생성 관련")
	class CreateTest {

		@DisplayName("고객 생성 테스트")
		@Test
		void testCase1() {
			// 객체
			Address address = new Address.Builder(
					"서울시", "서초구", "291-11"
			).build();

			Member actual = new Member.Builder(1L)
					.name("seok")
					.address(address)
					.build();

			assertThat(actual).isEqualTo(new Member.Builder(1L).build());
		}
	}
}