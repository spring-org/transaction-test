package com.example.transactiontest.application.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.example.transactiontest.application.member.stub.StubMember.address;
import static com.example.transactiontest.application.member.stub.StubMember.makeMember;
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
			Member actual = makeMember(id, null);

			Member expected = makeMember(id, null);

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
			Member actual = makeMember(id, null);

			Member expected = actual.update(name, null);
			assertThat(expected.getName()).isEqualTo(name);
			assertThat(expected.getAddress()).isNull();
		}

		@DisplayName("고객 정보 수정(address) 테스트")
		@CsvSource(value = {"1,서울시,논현로,1234"})
		@ParameterizedTest(name = "Id:{0} zipcode:{1} 등록")
		void testCase2(Long id, String city, String street, String zipcode) {
			Member actual = makeMember(id, null);

			Member expected = actual.update(null,address(city, street, zipcode));

			assertThat(expected.getName()).isNull();
			assertThat(expected.getAddress()).isEqualTo(address(city, street, zipcode));
		}

		@DisplayName("고객 정보 수정(address: zipcode) 테스트")
		@CsvSource(value = {"1,서울시,논현로,preZipCode,postZipCode"})
		@ParameterizedTest(name = "id:{0}에 해당하는 Member의 Address:zipcode 필드 {1}에서 {2}으로 수정")
		void testCase3(long id, String city, String street, String originZipCode, String updatedZipCode) {
			Member actual = makeMember(id, address(city, street, originZipCode));

			Member expected = actual.update(null, address(city, street, updatedZipCode));

			assertThat(expected.getName()).isNull();
			assertThat(expected.getAddress()).isEqualTo(address(city, street, updatedZipCode));
		}
	}

}