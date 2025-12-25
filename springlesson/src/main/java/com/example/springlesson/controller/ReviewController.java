package com.example.springlesson.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
      reviewService.getLatest5Reviews();
      
    }catch (Exception e) {
      e.printStackTrace();
      model.addAttribute("errMsg", "レビュー情報の取得に失敗しました。");
    }
    return "review/reviewList";
  }
  
  // レビュー投稿ページ
  @GetMapping("/new")
  public String newReview() {
    return "review/reviewForm";
  } 
}

