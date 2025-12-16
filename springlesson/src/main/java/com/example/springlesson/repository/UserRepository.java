package com.example.springlesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springlesson.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
