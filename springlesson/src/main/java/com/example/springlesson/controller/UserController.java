package com.example.springlesson.controller;

import java.security.Principal;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springlesson.entity.User;
import com.example.springlesson.repository.UserRepository;

@Controller
@RequestMapping("")
public class UserController {
  private final UserRepository userRepository;
  
  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  @GetMapping("/mypage")
  public String loginSuccess(HttpSession session, Principal principal) {
      // principal からメールアドレスを取得
      String email = principal.getName();
      
      // DBからユーザー情報を取得
      User user = userRepository.findByEmail(email);
      
      // セッションにUserオブジェクトを保存
      session.setAttribute("userInf", user);
      
      return "mypage"; // mypage.html に飛ぶ
  }

  
  
}
