package com.example.transactiontest.application.category.service;

import com.example.transactiontest.application.category.domain.Category;
import com.example.transactiontest.application.category.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
@DisplayName("카테고리 클래스 Command")
class CategoryCommandServiceTest {

	@InjectMocks
	private CategoryService categoryService;
	@Mock
	private CategoryRepository categoryRepository;

	@Nested
	@DisplayName("생성 관련")
	class CreateCategoryTest {

		@DisplayName("단일 생성 테스트")
		@Test
		void testCase1(@Mock Category category) {
			given(categoryRepository.save(any())).willReturn(category);

			categoryService.save(category.getId(), category.getName());

			then(categoryRepository).should().save(any());
		}
	}

	/**
	 * Method source 'dummy' must be static 이어야 하는데
	 * inner 클래스에서 MethodSource를 사용하기 위해서는 @TestInstance(PER_CLASS) 설정해서 사용 필요
	 */
	@TestInstance(PER_CLASS)
	@Nested
	@DisplayName("수정 관련")
	class UpdateCategoryTest {

		private Stream<Arguments> parentAndChild() {
			return Stream.of(
					Arguments.of(Category.of(1L, "서적")
							, Category.of(2L, "소설책"))
			);
		}

		@MethodSource("parentAndChild")
		@DisplayName("특정 카테고리 정보(상위 카테고리에 연결) 수정 테스트")
		@ParameterizedTest(name = "상위 카테고리:{0}의 하위 카테고리 추가")
		void testCase1(Category parent, Category child) {
			given(categoryRepository.findById(parent.getId())).willReturn(Optional.of(parent));

			Category actual = categoryService.addChild(parent.getId(), child.getId(), child.getName());

			assertThat(actual.getChild()).contains(child);
		}
	}


}