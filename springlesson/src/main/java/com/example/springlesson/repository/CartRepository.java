package com.example.springlesson.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springlesson.entity.CartItem;

public interface CartRepository extends JpaRepository<CartItem, Long> {

  Optional<CartItem> findByUserId(Long userId);
}
