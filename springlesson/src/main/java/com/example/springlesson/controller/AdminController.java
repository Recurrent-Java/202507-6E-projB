package com.example.springlesson.controller;

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

import com.example.springlesson.entity.Product;
import com.example.springlesson.entity.User;
import com.example.springlesson.form.AdminCustomerForm;
import com.example.springlesson.form.AdminProductForm;
import com.example.springlesson.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {


  private final AdminService adminService;

  public AdminController( AdminService adminService) {
    this.adminService = adminService;
   
  }
  
  @GetMapping("/login")
  public String login() {
    return "admin/login";
  }
  @GetMapping("/userManagement")
  public String userManagement(Model model) {
    try {
      List<User> userList = adminService.findAllUsers();
      model.addAttribute("userList", userList);
      return "admin/adminUser";
    } catch (Exception e) {
      model.addAttribute("errMsg", "ユーザー情報の取得中にエラーが発生しました。");
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
      return "admin/userManagement";
    }
    try {
      List<User> userList = adminService.findByIdIn(form.getUserIds());
      session.setAttribute("userCheckList", userList);
      model.addAttribute("userList", userList);
      return "admin/adminUserCheck";
    } catch (Exception e) {
      model.addAttribute("errMsg", "ユーザー情報の取得中にエラーが発生しました。");
      return "error/error";
    }
  }

  @PostMapping("/disableUser")
  public String disableUser(HttpSession session, Model model) {
    try {

      List<User> userList = (List<User>) session.getAttribute("userCheckList");
      for (User user : userList) {
        adminService.disableUser(user.getId());
      }
      session.removeAttribute("userCheckList");
      return "admin/finish";
    } catch (Exception e) {
      model.addAttribute("errorMessage", "ユーザーの無効化中にエラーが発生しました。");
      return "error/error";
    }
  }
  @GetMapping("/productManagement")
  public String productManagement(Model model) {
    try {
      List<Product> productList = adminService.findAllProducts();
      model.addAttribute("productList", productList);
      return "admin/adminProduct";
    } catch (Exception e) {
      model.addAttribute("errMsg", "商品情報の取得中にエラーが発生しました。");
      return "error/error";
    }
  }
  @PostMapping("/checkProduct")
  public String checkProduct( @Valid @ModelAttribute("AdminProductForm") AdminProductForm form,
      BindingResult bindingResult,
      HttpSession session,
      Model model) {
    if (bindingResult.hasErrors()) {
      //エラー時の処理
      return "admin/adminProduct";
    }
    try {
      List<Product> productList = adminService.findByIdInProducts(form.getProductIds());
      session.setAttribute("productCheckList", productList);
      model.addAttribute("productList", productList);
      return "admin/adminProductCheck";
    } catch (Exception e) {
      model.addAttribute("errMsg", "商品情報の取得中にエラーが発生しました。");
      return "error/error";
    }
  }
  @PostMapping("/disableProduct")
  public String disableProduct(HttpSession session, Model model) {
    try {

      List<Product> productList = (List<Product>) session.getAttribute("productCheckList");
      for (Product product : productList) {
        adminService.disableProduct(product.getId());
      }
      session.removeAttribute("productCheckList");
      return "admin/finish";
    } catch (Exception e) {
      model.addAttribute("errorMessage", "商品の無効化中にエラーが発生しました。");
      return "error/error";
    }
  }
}