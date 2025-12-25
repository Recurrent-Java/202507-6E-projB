package com.example.springlesson.controller;

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
  @GetMapping("/sign-up")
    public String signUp( Model model) {
    model.addAttribute("registForm", new RegistForm());
        return "signup/signup";
    }
  @PostMapping("/sign-up")
  public String signUpPost( @Valid @ModelAttribute("registForm") RegistForm form,
      BindingResult bindingResult,
      HttpSession session,
      Model model
      ) {
  if (bindingResult.hasErrors()) {
    //エラー時の処理
    return "signup/signup";
  }
  
  session.setAttribute("registForm", form);
  return "signup/checkSi";
  }
  @GetMapping("/correct")
  public String correct(HttpSession session, Model model) {
  RegistForm form = (RegistForm) session.getAttribute("registForm");
  model.addAttribute("registForm", form);
      return "signup/signup";
  }
  @PostMapping("/save")
  public String signUpComplete( HttpSession session,
      Model model
      ) {
    //セッションスコープより登録内容取得
    RegistForm form = (RegistForm) session.getAttribute("registForm");
    
    try {
      model.addAttribute("registForm", form);
      session.setAttribute("registForm", form);
   authService.SaveUser(form);
   return "mypage/mypage";
    } catch (Exception e) {
      //例外発生時の処理
      e.printStackTrace(); 
      model.addAttribute("errMsg", "ユーザー登録に失敗しました。");
      return "error/error";
    }
       
    }
  }
