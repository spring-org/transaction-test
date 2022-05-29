package com.example.transactiontest.application.category.service;

import com.example.transactiontest.application.category.domain.Category;
import com.example.transactiontest.application.category.exception.NotFoundCategoryException;
import com.example.transactiontest.application.category.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 카테고리 등록
 * 카테고리 조회
 * 카테고리 수정 (카테고리 명)
 */
@Service
@Transactional
public class CategoryService implements CategoryQueryService, CategoryCommandService {

	private final CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Transactional(readOnly = true)
	@Override
	public Category findCategory(Long categoryId) {
		return categoryRepository.findById(categoryId)
				.orElseThrow(() -> new NotFoundCategoryException("존재하지 않는 카테고리입니다. : {}", categoryId));
	}

	@Transactional(readOnly = true)
	@Override
	public List<Category> findCategory() {
		return categoryRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Category findParentCategory(Long categoryId) {
		Category parentCategory = findCategory(categoryId).getParent();
		return Optional.ofNullable(parentCategory)
				.orElseThrow(() -> new NotFoundCategoryException("상위 카테고리가 존재하지 않습니다."));
	}

	@Transactional(readOnly = true)
	@Override
	public List<Category> findChildCategories(Long parentCategoryId) {
		List<Category> childCategories = findCategory(parentCategoryId).getChild();
		return Optional.ofNullable(childCategories)
				.orElseThrow(() -> new NotFoundCategoryException("하위 카테고리가 존재하지 않습니다."));
	}

	@Transactional
	@Override
	public Category save(String categoryName) {
		Category category = Category.of(categoryName);
		return categoryRepository.save(category);
	}

	@Transactional
	@Override
	public Category addChild(Long categoryId, String subCategoryName) {
		Category subCategory = Category.of(subCategoryName);
		return findCategory(categoryId)
				.addChildCategory(subCategory);
	}
}
