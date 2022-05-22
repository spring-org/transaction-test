package com.example.transactiontest.application.product.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.example.transactiontest.application.product.stub.ItemStub.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("상품")
class ItemTest {


	@Nested
	@DisplayName("생성 관련")
	class CreateTest {

		private Item album;
		private Item book;
		private Item movie;

		@BeforeEach
		void setUp() {
			album = album(1L, 1000, 10, "이름", "작가", "그외");
			book = book(2L, 500, 2, "책이름", "저자", "ISBN");
			movie = movie(3L, 15000, 9999, "영화이름", "감독", "주연");
		}

		@DisplayName("앨범 생성 및 비교")
		@Test
		void testCase1() {
			Item expected = album(1L, 1000, 10, "이름", "작가", "그외");
			assertThat(album).usingRecursiveComparison().isEqualTo(expected);
		}

		@DisplayName("책 생성 및 비교")
		@Test
		void testCase2() {
			Item expected = book(2L, 500, 2, "책이름", "저자", "ISBN");
			assertThat(book).usingRecursiveComparison().isEqualTo(expected);
		}

		@DisplayName("영화 생성 및 비교")
		@Test
		void testCase3() {
			Item expected = movie(3L, 15000, 9999, "영화이름", "감독", "주연");
			assertThat(movie).usingRecursiveComparison().isEqualTo(expected);
		}
	}
}