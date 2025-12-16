package com.example.springlesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springlesson.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
public User findByEmail(String email);
public User save(User user);

}
