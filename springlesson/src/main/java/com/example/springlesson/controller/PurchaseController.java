package com.example.springlesson.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springlesson.entity.CartItem;
import com.example.springlesson.entity.User;
import com.example.springlesson.service.CartService;
import com.example.springlesson.service.UserService;

@Controller
public class PurchaseController {

    private final CartService cartService;
    private final UserService userService;

    public PurchaseController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    // ユーザーのカート一覧を表示
    @GetMapping("/purchase/cart")
    public String viewCart(@RequestParam String email, Model model) {
        // メールから User を取得
        User user = userService.findByEmail(email);

        // User を元に CartItem を取得
        List<CartItem> cartItems = cartService.findCartItems(user);

        // 合計金額も計算してモデルに追加
        int total = cartService.calcTotal(cartItems);

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);

        return "cart/view"; // 表示用テンプレート名
    }
}
