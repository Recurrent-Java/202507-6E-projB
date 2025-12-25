package com.example.springlesson.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springlesson.entity.Review;
import com.example.springlesson.repository.ReviewRepository;

@Service
public class TopService {
private final ReviewRepository reviewRepository;

public TopService(ReviewRepository reviewRepository) {
  this.reviewRepository = reviewRepository;
}
@Transactional
public List<Review> getTop2Reviews() {
    return reviewRepository.findTop2ByOrderByIdAsc();

}

}
