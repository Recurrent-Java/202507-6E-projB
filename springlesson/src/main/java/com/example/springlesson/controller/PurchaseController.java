package com.example.springlesson.controller;

import java.util.ArrayList;
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

    /**
     * カート画面を表示
     * 既存のメソッド
     */
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
        return "cart/view"; // templates/cart/cart.html を表示
    }

    /**
     * カート → お届け先入力画面
     */
    @GetMapping("/purchase/purchase-inf")
    public String purchaseInf() {
        // モデル不要。画面遷移だけ
        return "purchase/purchase-inf"; // templates/purchase/purchase-inf.html
    }

    /**
     * お届け先入力 → 最終確認画面
     */
    /**
     * お届け先入力 → 最終確認画面
     * データ保持無視、画面遷移だけ
     */
    @GetMapping("/purchase/purchase-in")
    public String purchaseIn(Model model) {
        // 空のカートリストと合計金額を設定
        List<CartItem> cartItems = new ArrayList<>();
        int totalPrice = 0;

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);

        return "purchase/purchase-in"; // templates/purchase/purchase-in.html
    }
}

