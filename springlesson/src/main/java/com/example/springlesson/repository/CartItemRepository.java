package com.example.springlesson.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springlesson.entity.CartItem;
import com.example.springlesson.entity.Product;
import com.example.springlesson.entity.User;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // ユーザーのカートアイテム一覧
    List<CartItem> findByUser(User user);

    // ユーザーと商品に紐づくカートアイテム
    Optional<CartItem> findByUserAndProduct(User user, Product product);

    // もしメールでユーザー検索もしたい場合
    List<CartItem> findByUserUsername(String username);
}
