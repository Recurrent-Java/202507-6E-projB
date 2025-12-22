package com.example.springlesson.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springlesson.entity.CartItem;
import com.example.springlesson.entity.User;
import com.example.springlesson.security.UserDetailsImpl;
import com.example.springlesson.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

  private final CartService cartService;

  public CartController(CartService cartService) {
    this.cartService = cartService;
  }

  @GetMapping 
public String cart(@AuthenticationPrincipal UserDetailsImpl principal,
                     Model model) {

    User user = principal.getUser(); 
    List<CartItem> cartItems = cartService.findCartItems(user);

    model.addAttribute("cartItems", cartItems);
    model.addAttribute("total", cartService.calcTotal(cartItems));

    return "cart/cart";
  }

  @PostMapping("/add")
  public String add(@AuthenticationPrincipal UserDetailsImpl principal,
                    @RequestParam Long productId,
                    @RequestParam Integer quantity) {

    cartService.add(principal.getUser(), productId, quantity);
    return "redirect:/cart";
  }

  @PostMapping("/update")
  public String update(@AuthenticationPrincipal UserDetailsImpl principal,
                       @RequestParam Long productId,
                       @RequestParam Integer quantity) {

    cartService.updateQuantity(principal.getUser(), productId, quantity);
    return "redirect:/cart";
  }

  @PostMapping("/remove")
  public String remove(@AuthenticationPrincipal UserDetailsImpl principal,
                       @RequestParam Long productId) {

    cartService.remove(principal.getUser(), productId);
    return "redirect:/cart";
  }
  
}

