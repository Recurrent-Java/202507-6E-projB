package com.example.springlesson.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springlesson.entity.Review;
import com.example.springlesson.entity.User;
import com.example.springlesson.form.ReviewForm;
import com.example.springlesson.repository.ReviewRepository;

@Service
public class ReviewService {
  private final ReviewRepository reviewRepository;
  
  public ReviewService(ReviewRepository reviewRepository) {
    this.reviewRepository = reviewRepository;
  }
  
 @Transactional
 public List<Review> getLatest5Reviews() {
   return reviewRepository.findTop5ByOrderByCreatedAtDesc();
 }
 
 @Transactional
 public void createReview(User loginUser, ReviewForm form) {
   Review review = new Review();
   // review.setReviewerName(form.getReviewerName());
   // review.setTitle(form.getTitle());
   review.setComment(form.getComment());
   review.setUser(loginUser);
   
   reviewRepository.save(review);
 }
}

