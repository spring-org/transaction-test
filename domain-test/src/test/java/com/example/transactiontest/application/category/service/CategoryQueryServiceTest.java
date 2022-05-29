package com.example.transactiontest.application.category.service;

import com.example.transactiontest.application.category.domain.Category;
import com.example.transactiontest.application.category.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
@DisplayName("카테고리 클래스 Query")
class CategoryQueryServiceTest {

	@InjectMocks
	private CategoryService categoryQueryService;

	@Mock
	private CategoryRepository categoryRepository;

	@Nested
	@DisplayName("조회 관련")
	class FindCategoryTest {

		@DisplayName("카테고리 단일 조회 테스트")
		@Test
		void testCase1(@Mock Category category) {
			given(categoryRepository.findById(any())).willReturn(Optional.ofNullable(category));

			categoryQueryService.findCategory(any());

			then(categoryRepository).should().findById(any());
		}

		@DisplayName("카테고리 전체 조회 테스트")
		@Test
		void testCase2(@Mock List<Category> categories) {
			given(categoryRepository.findAll()).willReturn(categories);

			categoryQueryService.findCategory();

			then(categoryRepository).should().findAll();
		}
	}

	@Nested
	@DisplayName("검색 관련")
	class SearchCategoryTest {
		@DisplayName("카테고리 상위 노드 조회 테스트")
		@Test
		void testCase1(@Mock Category parent, @Mock Category child) {

			given(categoryRepository.findById(anyLong())).willReturn(Optional.of(child));
			given(child.getParent()).willReturn(parent);

			Category parentCategory = categoryQueryService.findParentCategory(anyLong());

			assertThat(parent).isEqualTo(parentCategory);
		}

		@DisplayName("카테고리 하위 노드 조회 테스트")
		@Test
		void testCase2(@Mock Category parent, @Mock Category child) {

			given(categoryRepository.findById(anyLong())).willReturn(Optional.of(parent));
			given(parent.getChild()).willReturn(Collections.singletonList(child));

			List<Category> childCategories = categoryQueryService.findChildCategories(parent.getId());

			assertThat(childCategories).contains(child);
		}
	}
}