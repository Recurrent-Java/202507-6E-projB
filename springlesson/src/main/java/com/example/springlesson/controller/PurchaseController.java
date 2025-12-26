package com.example.springlesson.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springlesson.entity.CartItem;
import com.example.springlesson.service.CartService;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

    private final CartService cartService;

    public PurchaseController(CartService cartService) {
        this.cartService = cartService;
    }

    /** お届け先設定画面 */
    @GetMapping("/purchase-in")
    public String purchaseIn(Principal principal, Model model) {
        if (principal == null) {
            // ログインしていなければログイン画面にリダイレクト
            return "redirect:/auth/login";
        }

        String email = principal.getName();

        // カート情報を取得
        List<CartItem> cartItems = cartService.getCartItemsByUserEmail(email);
        int totalQuantity = cartItems.stream().mapToInt(CartItem::getQuantity).sum();
        int totalPrice = cartItems.stream().mapToInt(item -> item.getQuantity() * item.getUnitPrice()).sum();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalQuantity", totalQuantity);
        model.addAttribute("totalPrice", totalPrice);

        // 必要ならユーザー情報も追加
        model.addAttribute("userEmail", email);

        return "purchase/purchase-in"; // purchase/purchase-in.html に遷移
    }

}
