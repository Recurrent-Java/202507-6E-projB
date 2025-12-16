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

import com.example.springlesson.entity.CartItem;
import com.example.springlesson.form.OrderForm;
import com.example.springlesson.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 注文入力画面表示
     */
    @GetMapping
    public String orderInput(HttpSession session, Model model) {

        @SuppressWarnings("unchecked")
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            model.addAttribute("errMsg", "カートに商品がありません。");
            return "error/error";
        }

        model.addAttribute("cart", cart);
        model.addAttribute("orderForm", new OrderForm());

        return "order/order-input";
    }

    /**
     * 注文確定処理
     */
    @PostMapping("/confirm")
    public String confirmOrder(
            @Valid @ModelAttribute("orderForm") OrderForm form,
            BindingResult bindingResult,
            HttpSession session,
            Principal principal,
            Model model) {

        @SuppressWarnings("unchecked")
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            model.addAttribute("errMsg", "カートに商品がありません。");
            return "error/error";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("cart", cart);
            return "order/order-input";
        }

        try {
            orderService.createOrder(principal.getName(), cart, form);

            // 注文完了後、カートをクリア
            session.removeAttribute("cart");

            return "order/order-complete";

        } catch (Exception e) {
            model.addAttribute("errMsg", e.getMessage());
            return "error/error";
        }
    }

    /**
     * 注文履歴表示（会員のみ）
     */
    @GetMapping("/history")
    public String orderHistory(Principal principal, Model model) {

        model.addAttribute("orders",
                orderService.findOrdersByEmail(principal.getName()));

        return "order/order-history";
    }
}
