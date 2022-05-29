package com.example.transactiontest.application.product.repository;

import com.example.transactiontest.application.product.domain.Album;
import com.example.transactiontest.application.product.domain.Book;
import com.example.transactiontest.application.product.domain.Item;
import com.example.transactiontest.application.product.domain.Movie;
import com.example.transactiontest.application.product.exception.NotFoundItemException;
import com.example.transactiontest.application.product.stub.ItemStub;
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
import org.springframework.transaction.annotation.Transactional;

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
			Item item = ItemStub.book(1000, 10, "책", "저자", "isbn");
			Item savedItem = itemRepository.save(item);

			assertThat(savedItem).isInstanceOf(Book.class);
		}

		@DisplayName("앨범 상품 생성 확인 테스트")
		@Test
		void testCase2() {
			Item item = ItemStub.album(500, 1, "앨범", "아티스트", "팝");
			Item savedItem = itemRepository.save(item);

			assertThat(savedItem).isInstanceOf(Album.class);
		}

		@DisplayName("영화 상품 생성 확인 테스트")
		@Test
		void testCase3() {
			Item item = ItemStub.movie(15000, 2, "영화", "감독", "주연");
			Item savedItem = itemRepository.save(item);

			assertThat(savedItem).isInstanceOf(Movie.class);
		}
	}

	@Nested
	@DisplayName("상품 등록 및 조회 테스트")
	class FindItemTest {

		@BeforeEach
		void setUp() {
			itemRepository.deleteAll();
			itemRepository.save(ItemStub.book(15000, 2, "영화", "저자", "isbn"));
			itemRepository.save(ItemStub.movie(15000, 2, "영화", "감독", "주연"));
			itemRepository.save(ItemStub.movie(15000, 2, "영화", "감독", "주연"));
			itemRepository.save(ItemStub.album(15000, 2, "영화", "아티스트", "팝"));
			itemRepository.save(ItemStub.movie(15000, 2, "영화", "감독", "주연"));
		}

		@DisplayName("특정 상품 조회 테스트")
		@ValueSource(longs = 1L)
		@ParameterizedTest(name = "Id:{0} 로 상품 조회")
		void testCase2(long id) {
			Item item = getItem(id);
			assertThat(item.getId()).isEqualTo(1L);
			assertThat(item).isInstanceOf(Book.class);
		}

		@DisplayName("특정 상품 수정 테스트")
		@ValueSource(longs = 1L)
		@ParameterizedTest(name = "Id:{0} 상품 수정")
		@Transactional(readOnly = true)
		void testCase3(long id) {
			Item item = getItem(id);
			item.addStock(2);

			itemRepository.flush(); // update query
			Item updatedItem = getItem(id);

			assertThat(updatedItem.getStockQuantity()).isEqualTo(2);
		}
	}

	private Item getItem(long id) {
		return itemRepository.findById(id)
				.orElseThrow(() -> new NotFoundItemException("존재하지 않는 상품입니다. : {}", id));
	}
}