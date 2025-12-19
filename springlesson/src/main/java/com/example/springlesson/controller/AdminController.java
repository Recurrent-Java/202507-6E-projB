package com.example.springlesson.controller;

import java.security.Principal;
import java.util.List;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springlesson.entity.User;
import com.example.springlesson.form.AdminCustomerForm;
import com.example.springlesson.repository.UserRepository;
import com.example.springlesson.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

  private final UserRepository userRepository;
  private final AdminService adminService;

  public AdminController(UserRepository userRepository, AdminService adminService) {
    this.adminService = adminService;
    this.userRepository = userRepository;
  }

  @GetMapping("/adminPage")
  public String showAdminPage(HttpSession session, Principal principal) {
    //principal からメールアドレスを取得
    String email = principal.getName();

    // DBからユーザー情報を取得
    User user = userRepository.findByEmail(email);

    // セッションにUserオブジェクトを保存
    session.setAttribute("userInf", user);

    return "admin/adminPage";
  }

  @GetMapping("/userManagement")
  public String userManagement(Model model) {
    try {
      List<User> userList = adminService.findAllUsers();
      model.addAttribute("userList", userList);
      return "admin/adminUser";
    } catch (Exception e) {
      model.addAttribute("errorMessage", "ユーザー情報の取得中にエラーが発生しました。");
      return "error/error";
    }
  }

  @PostMapping("/checkUser")
  public String checkUser( @Valid @ModelAttribute("AdminCustomerForm") AdminCustomerForm form,
      BindingResult bindingResult,
      HttpSession session,
      Model model) {
    if (bindingResult.hasErrors()) {
      //エラー時の処理
      return "admin/userManagement/";
    }
    try {
      List<User> userList = adminService.findByIdIn(form.getUserIds());
      session.setAttribute("userCheckList", userList);
      model.addAttribute("userList", userList);
      return "admin/adminUserCheck";
    } catch (Exception e) {
      model.addAttribute("errorMessage", "ユーザー情報の取得中にエラーが発生しました。");
      return "error/error";
    }
  }

  @PostMapping("/disableUser")
  public String disableUser(HttpSession session, Model model) {
    try {

      List<User> userList = (List<User>) session.getAttribute("userCheckList");
      for (User user : userList) {
        adminService.deleteUser(user.getId());
      }
      session.removeAttribute("userCheckList");
      return "/admin/finish";
    } catch (Exception e) {
      model.addAttribute("errorMessage", "ユーザーの無効化中にエラーが発生しました。");
      return "error/error";
    }
  }
}