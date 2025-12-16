package com.example.springlesson.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springlesson.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

  List<CartItem> findByCartId(Long cartId);

  Optional<CartItem> findByCartIdAndProductId(Long cartId, Integer productId);
}

