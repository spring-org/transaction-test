package com.example.transactiontest.application.product.repository;

import com.example.transactiontest.application.product.domain.Album;
import com.example.transactiontest.application.product.domain.Book;
import com.example.transactiontest.application.product.domain.Item;
import com.example.transactiontest.application.product.domain.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Item 클래스")
@ActiveProfiles(profiles = "test")
class ItemRepositoryTest {

	@Autowired
	private ItemRepository itemRepository;

	@Nested
	@DisplayName("하위 상품 관련")
	class CreateTest {

		@DisplayName("책 상품 생성 확인")
		@Test
		void testCase1() {
			Item item = new Book.Builder(1L).build();
			Item savedItem = itemRepository.save(item);

			assertThat(savedItem).isInstanceOf(Book.class);
		}

		@DisplayName("앨범 상품 생성 확인 테스트")
		@Test
		void testCase2() {
			Item item = new Album.Builder(1L).build();
			Item savedItem = itemRepository.save(item);

			assertThat(savedItem).isInstanceOf(Album.class);
		}

		@DisplayName("영화 상품 생성 확인 테스트")
		@Test
		void testCase3() {
			Item item = new Movie.Builder(1L).build();
			Item savedItem = itemRepository.save(item);

			assertThat(savedItem).isInstanceOf(Movie.class);
		}
	}

}