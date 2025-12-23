package com.example.springlesson.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springlesson.entity.Order;
import com.example.springlesson.entity.User;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserOrderByOrderDatetimeDesc(User user);
}
