package com.example.springlesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springlesson.entity.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,ProductCategory> {

}
