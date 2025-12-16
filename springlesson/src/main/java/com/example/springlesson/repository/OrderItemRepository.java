package com.example.springlesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springlesson.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
