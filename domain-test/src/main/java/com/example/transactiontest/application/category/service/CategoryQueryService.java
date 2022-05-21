package com.example.transactiontest.application.category.service;

import com.example.transactiontest.application.category.domain.Category;

import java.util.List;

public interface CategoryQueryService {
	Category findCategory(Long categoryId);

	List<Category> findCategory();
}
