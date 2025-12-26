package com.example.springlesson.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.springlesson.entity.CartItem;
import com.example.springlesson.repository.CartItemRepository;

@Controller
public class PurchaseController {

    private final CartItemRepository cartItemRepository;

    public PurchaseController(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @GetMapping("/purchase")
    public String showPurchasePage(Model model, Principal principal) {
        // ユーザーに紐づくカートアイテムを取得
        List<CartItem> cartItems = cartItemRepository.findByUserUsername(principal.getName());

        // 合計金額を計算
        int totalPrice = cartItems.stream()
                .mapToInt(item -> item.getUnitPrice() * item.getQuantity())
                .sum();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);

        return "purchase/purchase-in";
    }
}
