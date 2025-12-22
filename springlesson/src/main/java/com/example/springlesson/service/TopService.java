package com.example.springlesson.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.springlesson.entity.Review;
import com.example.springlesson.repository.ReviewRepository;

@Service
public class TopService {
private final ReviewRepository reviewRepository;

public TopService(ReviewRepository reviewRepository) {
  this.reviewRepository = reviewRepository;
}

public List<Review> getTop2Reviews() {
  try {
    return reviewRepository.findTop2ByOrderByIdAsc();
  }catch(DataAccessException e) {
  throw new RuntimeException ("エラーのため表示できません");
    
  }

}

}
