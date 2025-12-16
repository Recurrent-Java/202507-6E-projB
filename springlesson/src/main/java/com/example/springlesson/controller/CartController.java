package com.example.springlesson.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springlesson.form.Item;
import com.example.springlesson.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

  private final CartService cartService;

  public CartController(CartService cartService) {
    this.cartService = cartService;
  }

  @GetMapping
  public String cart(@AuthenticationPrincipal LoginUser user,
                     Model model) {

    List<Item> cartItems = cartService.findCartItems(user.getId());
    model.addAttribute("cartItems", cartItems);
    model.addAttribute("total", cartService.calcTotal(cartItems));

    return "cart/cart";
  }

  @PostMapping("/add")
  public String add(@AuthenticationPrincipal LoginUser user,
                    @RequestParam Integer productId,
                    @RequestParam Integer quantity) {

    cartService.add(user.getId(), productId, quantity);
    return "redirect:/cart";
  }

  @PostMapping("/update")
  public String update(@AuthenticationPrincipal LoginUser user,
                       @RequestParam Integer productId,
                       @RequestParam Integer quantity) {

    cartService.updateQuantity(user.getId(), productId, quantity);
    return "redirect:/cart";
  }

  @PostMapping("/remove")
  public String remove(@AuthenticationPrincipal LoginUser user,
                       @RequestParam Integer productId) {

    cartService.remove(user.getId(), productId);
    return "redirect:/cart";
  }
}
