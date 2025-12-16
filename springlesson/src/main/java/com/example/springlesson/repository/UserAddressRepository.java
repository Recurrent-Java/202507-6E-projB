package com.example.springlesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springlesson.entity.User;

public interface UserAddressRepository extends JpaRepository<User, Integer> {
  // ログイン名で検索
  public User findByLogin(String login);
}
