package com.example.transactiontest.application.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("고객 클래스")
class MemberTest {

	@Nested
	@DisplayName("생성 관련")
	class CreateTest {

		@DisplayName("고객 생성 테스트")
		@ParameterizedTest(name = "특정 고객의 PK 값: {0}")
		@ValueSource(longs = 1L)
		void testCase1(Long id) {
			// 객체
			Member actual = new Member.Builder(id).build();

			Member expected = new Member.Builder(id).build();

			assertThat(actual).isEqualTo(expected);
		}
	}

	@Nested
	@DisplayName("수정 관련")
	class UpdateMemberTest {

		@DisplayName("고객 정보 수정(name) 테스트")
		@CsvSource(value = {"1,sr"})
		@ParameterizedTest(name = "Id:{0} zipcode:{1} 등록")
		void testCase1(Long id, String name) {
			Member actual = new Member.Builder(id).build();

			Member expected = actual.update(name, null);
			assertThat(expected.getName()).isEqualTo(name);
			assertThat(expected.getAddress()).isNull();
		}

		@DisplayName("고객 정보 수정(address) 테스트")
		@CsvSource(value = {"1,1234"})
		@ParameterizedTest(name = "Id:{0} zipcode:{1} 등록")
		void testCase2(Long id, String zipcode) {
			Member actual = new Member.Builder(id).build();

			Member expected = actual.update(null, address(zipcode));

			assertThat(expected.getName()).isNull();
			assertThat(expected.getAddress()).isEqualTo(address(zipcode));
		}

		@DisplayName("고객 정보 수정(address: zipcode) 테스트")
		@CsvSource(value = {"1,1234,3333"})
		@ParameterizedTest(name = "zipcode 필드 {0}에서 {1}으로 수정")
		void testCase3(long id, String originZipCode, String updatedZipCode) {
			Member actual = new Member.Builder(id)
					.address(address(originZipCode))
					.build();

			Member expected = actual.update(null, address(updatedZipCode));

			assertThat(expected.getName()).isNull();
			assertThat(expected.getAddress()).isEqualTo(address(updatedZipCode));
		}
	}

	private Address address(String zipcode) {
		return new Address.Builder(
				"서울시", "서초구", zipcode
		).build();
	}
}