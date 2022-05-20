package com.example.transactiontest.application.product.repository;

import com.example.transactiontest.application.product.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
