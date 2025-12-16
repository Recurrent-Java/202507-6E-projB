package com.example.springlesson.controller;

import java.security.Principal;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springlesson.form.RegistForm;
import com.example.springlesson.service.AuthService;

@Controller
@RequestMapping("/auth")
public class AuthController {
  
  private final AuthService authService;
 

public AuthController(AuthService authService ) {
    this.authService = authService;
  }

@GetMapping("/login")
    public String login() {
  
        return "login/login";
    }
  
  @GetMapping("/logout")
    public String logout(HttpSession HttpSession) {
         HttpSession.removeAttribute("userInf");
        return "/index";
    }
  @GetMapping("/sign-up")
    public String signUp() {
        return "/sign-up/sign-up";
    }
  @PostMapping("login") 
    public String loginPost() {
        return "login/login";
    }
  @PostMapping("sign-up")
    public String signUpPost( @Valid @ModelAttribute("RegistForm") RegistForm form,
        BindingResult bindingResult,
        HttpSession session,
        Principal principal,
        Model model
        ) {
    if (bindingResult.hasErrors()) {
      //エラー時の処理
      return "/error/error";
    }
    try {
   authService.SaveUser(form);
      return "login/login";
    } catch (Exception e) {
      //例外発生時の処理
      model.addAttribute("errMsg", "ユーザー登録に失敗しました。");
      return "/error/error";
    }
       
    }
  }
