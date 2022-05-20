package com.example.transactiontest.application.order.repository;

import com.example.transactiontest.application.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
