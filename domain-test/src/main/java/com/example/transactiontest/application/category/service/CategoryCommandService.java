package com.example.transactiontest.application.category.service;

import com.example.transactiontest.application.category.domain.Category;

import java.util.List;

public interface CategoryCommandService {
	Category save(Long categoryId, String categoryName);

	Category findParentCategory(Long categoryId);

	List<Category> findChildCategories(Long categoryId);

	Category addChild(Long categoryId, Long subCategoryId, String subCategoryName);
}
