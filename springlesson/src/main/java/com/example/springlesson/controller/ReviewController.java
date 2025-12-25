package com.example.springlesson.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/review")
public class ReviewController {
  // レビュー一覧ページ
  @GetMapping
  public String list() {
    return "review/reviewList";
  }
  
  // レビュー投稿ページ
  @GetMapping("/new")
  public String newReview() {
    return "review/reviewForm";
  } 
}

