package com.example.springlesson.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springlesson.entity.Product;
import com.example.springlesson.entity.User;
import com.example.springlesson.repository.ProductRepository;
import com.example.springlesson.repository.UserRepository;

@Service
public class AdminService {
private final UserRepository userRepository;
private final ProductRepository productRepository;

  public AdminService(
      UserRepository userRepository,
      ProductRepository productRepository
      ) {
    this.userRepository = userRepository;
    this.productRepository = productRepository;
  }
  @Transactional
  public void disableUser(Long userId) {
  User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("ユーザーが見つかりません。ID: " + userId));
      
  user.setWithdrawFlag(true);
}
  @Transactional
  public void disableProduct(Long productId) {
   Product Product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("商品が見つかりません。ID: " + productId));
    Product.setIsActive(false);
  }
  @Transactional
  public List<User> findAllUsers() {
    return userRepository.findAll();
    }
  @Transactional
  public List<Product> findAllProducts() {
    return productRepository.findAll();
  }

    @Transactional 
    public List<User> findByIdIn(List<Long> userIds) {
      return  userRepository.findByIdIn(userIds);
    }
  
@Transactional 
  public List<Product> findByIdInProducts(List<Long> productIds) {
    return  productRepository.findByIdIn(productIds);
  }
}

  
