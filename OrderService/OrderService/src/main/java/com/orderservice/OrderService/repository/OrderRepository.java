package com.orderservice.OrderService.repository;

import com.orderservice.OrderService.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
