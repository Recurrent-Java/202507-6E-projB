package com.example.springlesson.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springlesson.entity.CartItem;
import com.example.springlesson.entity.Product;
import com.example.springlesson.entity.User;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

  List<CartItem> findByUser(User user);

  Optional<CartItem> findByUserAndProduct(User user, Product product);
}

