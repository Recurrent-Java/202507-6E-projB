package com.example.springlesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springlesson.entity.UserAddress;

public interface OrderRepository extends JpaRepository<UserAddress, Integer> {
  // テーブルの全カラムを登録する save(Purchase purchase) -> INSERT文はJPAが自動で作成
}
