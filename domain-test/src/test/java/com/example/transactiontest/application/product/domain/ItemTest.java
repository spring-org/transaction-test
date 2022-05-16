package com.example.transactiontest.application.product.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("상품")
class ItemTest {


	@Nested
	@DisplayName("생성 관련")
	class Creator {

		private Item actual;

		@BeforeEach
		void setUp() {
			actual = Album.builder()
					.id(1L).name("이름").artist("작가").etc("그외")
					.price(1000)
					.stockQuantity(10)
					.build();
		}

		@DisplayName("앨범 생성하기")
		@Test
		void testCase1() {
			Item expected = Album.builder()
					.id(1L)
					.price(1000)
					.stockQuantity(10)
					.build();

			assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
		}
	}
}