package com.example.transactiontest.application.category.repository;

import com.example.transactiontest.application.category.domain.Category;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("카테고리 클래스")
class CategoryRepositoryTest {
	private Category category;

	@BeforeEach
	void setUp() {
		category = Category.of(1L, "1depth");
	}

	@Nested
	@DisplayName("하위 카테고리 관련")
	class ChildTest {

		@DisplayName("카테고리1 생성 및 추가")
		@Test
		void testCase1() {
			Category child1 = Category.of(2L, "2-1depth");
			category.addChildCategory(child1);
			assertThat(category.getChild()).contains(child1);
			assertThat(category.getChild()).hasSize(1);
		}

		@DisplayName("카테고리2 생성 및 추가")
		@Test
		void testCase2() {
			Category child2 = Category.of(3L, "2-2depth");
			Category child3 = Category.of(4L, "2-3depth");
			category.addChildCategory(child2);
			category.addChildCategory(child3);
			assertThat(category.getChild()).contains(child2, child3);
			assertThat(category.getChild()).hasSize(2);
		}
	}

	@Nested
	@DisplayName("상위 카테고리 관련")
	class ParentTest {

		@DisplayName("카테고리1 생성 및 추가")
		@Test
		void testCase1() {
			Category child1 = Category.of(2L, "2-1depth");
			category.addChildCategory(child1);

			assertThat(child1.getParent()).isEqualTo(category);
		}
	}
}