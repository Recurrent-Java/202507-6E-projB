package com.example.springlesson.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springlesson.entity.CartItem;
import com.example.springlesson.entity.Product;
import com.example.springlesson.entity.User;
import com.example.springlesson.repository.CartItemRepository;

@Service
@Transactional
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final ProductService productService;
    private final UserService userService; // ← UserService を追加

    public CartService(CartItemRepository cartItemRepository, ProductService productService, UserService userService) {
        this.cartItemRepository = cartItemRepository;
        this.productService = productService;
        this.userService = userService;
    }

    // カート一覧取得（既存）
    public List<CartItem> findCartItems(User user) {
        return cartItemRepository.findByUser(user);
    }

    // カート一覧取得（メールから取得する新メソッド）
    public List<CartItem> getCartItemsByUserEmail(String email) {
        User user = userService.findByEmail(email); // UserServiceでメールからUser取得
        return findCartItems(user); // 既存メソッドを再利用
    }

    // カート追加
    public void add(User user, Long productId, Integer quantity) {
        if (quantity == null || quantity <= 0) return;

        Product product = productService.findById(productId);

        CartItem item = cartItemRepository.findByUserAndProduct(user, product).orElse(null);

        if (item != null) {
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            CartItem newItem = new CartItem();
            newItem.setUser(user);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            newItem.setUnitPrice(product.getPrice());
            cartItemRepository.save(newItem);
        }
    }

    // 数量変更
    public void updateQuantity(User user, Long productId, Integer quantity) {
        Product product = productService.findById(productId);

        cartItemRepository.findByUserAndProduct(user, product).ifPresent(item -> {
            if (quantity <= 0) {
                cartItemRepository.delete(item);
            } else {
                item.setQuantity(quantity);
            }
        });
    }

    // 削除
    public void remove(User user, Long productId) {
        Product product = productService.findById(productId);

        cartItemRepository.findByUserAndProduct(user, product).ifPresent(cartItemRepository::delete);
    }

    // 合計金額
    public int calcTotal(List<CartItem> items) {
        return items.stream().mapToInt(i -> i.getUnitPrice() * i.getQuantity()).sum();
    }
}
