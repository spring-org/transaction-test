package com.example.transactiontest.application.order.repository;

import com.example.transactiontest.application.order.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
