package com.example.transactiontest.application.product.repository;

import com.example.transactiontest.application.product.domain.Album;
import com.example.transactiontest.application.product.domain.Book;
import com.example.transactiontest.application.product.domain.Item;
import com.example.transactiontest.application.product.domain.Movie;
import com.example.transactiontest.application.product.exception.NotFoundItemException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

	@Nested
	@DisplayName("상품 등록 및 조회 테스트")
	class FindItemTest {

		@BeforeEach
		void setUp() {
			itemRepository.save(new Book.Builder(1L).build());
			itemRepository.save(new Movie.Builder(2L).build());
			itemRepository.save(new Album.Builder(3L).build());
			itemRepository.save(new Album.Builder(4L).build());
			itemRepository.save(new Album.Builder(5L).build());
		}

		/**
		 * select
		 * item0_.ITEM_ID as item_id2_3_,
		 * item0_.name as name3_3_,
		 * item0_.price as price4_3_,
		 * item0_.stockQuantity as stockqua5_3_,
		 * item0_.artist as artist6_3_,
		 * item0_.etc as etc7_3_,
		 * item0_.author as author8_3_,
		 * item0_.isbn as isbn9_3_,
		 * item0_.actor as actor10_3_,
		 * item0_.director as directo11_3_,
		 * item0_.DTYPE as dtype1_3_
		 * from
		 * Item item0_ limit ? offset ?
		 * <p>
		 * select
		 * count(item0_.ITEM_ID) as col_0_0_
		 * from
		 * Item item0_
		 */
		@DisplayName("상품 전체 조회 페이징 테스트")
		@Test
		void testCase1() {
			Pageable pageable = PageRequest.of(1, 2);
			Page<Item> itemPage = itemRepository.findAll(pageable);

			assertThat(itemPage.getTotalPages()).isEqualTo(3);
		}

		@ValueSource(longs = 1L)
		@ParameterizedTest(name = "특정 상품 조회 테스트")
		void testCase2(long id) {
			Item item = itemRepository.findById(id)
					.orElseThrow(() -> new NotFoundItemException("존재하지 않는 상품입니다. : {}", id));
			assertThat(item.getId()).isEqualTo(1L);
			assertThat(item).isInstanceOf(Book.class);
		}
	}
}