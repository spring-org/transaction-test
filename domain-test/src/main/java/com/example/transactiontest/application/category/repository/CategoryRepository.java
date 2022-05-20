package com.example.transactiontest.application.category.repository;

import com.example.transactiontest.application.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
