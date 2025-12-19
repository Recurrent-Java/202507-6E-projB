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

    public CartService(CartItemRepository cartItemRepository,ProductService productService) {
        this.cartItemRepository = cartItemRepository;
        this.productService = productService;
    }

    // カート一覧取得
    public List<CartItem> findCartItems(User user) {
        return cartItemRepository.findByUser(user);
    }

    // カート追加
    public void add(User user, Integer productId, Integer quantity) {
        if (quantity == null || quantity <= 0) return;

        Product product = productService.findById(productId);

        CartItem item =cartItemRepository.findByUserAndProduct(user, product).orElse(null);

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
    public void updateQuantity(User user, Integer productId, Integer quantity) {
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
    public void remove(User user, Integer productId) {
        Product product = productService.findById(productId);

        cartItemRepository.findByUserAndProduct(user, product).ifPresent(cartItemRepository::delete);
    }

    // 合計金額
    public int calcTotal(List<CartItem> items) {
        return items.stream().mapToInt(i -> i.getUnitPrice() * i.getQuantity()).sum();
    }
}
