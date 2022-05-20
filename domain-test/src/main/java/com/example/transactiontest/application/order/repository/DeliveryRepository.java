package com.example.transactiontest.application.order.repository;

import com.example.transactiontest.application.order.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
