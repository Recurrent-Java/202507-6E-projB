package com.example.springlesson.controller;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springlesson.entity.Review;
import com.example.springlesson.service.ReviewService;

@Controller
@RequestMapping("/review")
public class ReviewController {
  private final ReviewService reviewService;
  
  public ReviewController(ReviewService reviewService) {
    this.reviewService = reviewService;
  }
  // レビュー一覧ページ
  @GetMapping
  public String list(Model model) {
    try {
      List<Review> reviewList = reviewService.getLatest5Reviews();
      model.addAttribute("reviews", reviewList);
      
    }catch (DataAccessException e) {
      throw new RuntimeException ("エラーのため表示できません");
      
    }
    return "review/reviewList";
  }
  
  // レビュー投稿ページ
  @GetMapping("/new")
  public String newReview() {
    return "review/reviewForm";
  } 
}

