package com.example.springlesson.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springlesson.dto.ProductDTO;
import com.example.springlesson.entity.Cart;
import com.example.springlesson.entity.CartItem;
import com.example.springlesson.form.Item;
import com.example.springlesson.repository.CartItemRepository;
import com.example.springlesson.repository.CartRepository;

@Service
@Transactional
public class CartService {

  private final CartRepository cartRepository;
  private final CartItemRepository cartItemRepository;
  private final ProductService productService;

  public CartService(CartRepository cartRepository,
                     CartItemRepository cartItemRepository,
                     ProductService productService) {
    this.cartRepository = cartRepository;
    this.cartItemRepository = cartItemRepository;
    this.productService = productService;
  }

  // ======================
  // カート取得（なければ作成）
  // ======================
  private Cart getOrCreateCart(Long userId) {
    return cartRepository.findByUserId(userId)
        .orElseGet(() -> cartRepository.save(new Cart(userId)));
  }

  // ======================
  // カート一覧取得（画面表示用）
  // ======================
  public List<Item> findCartItems(Long userId) {
    Cart cart = getOrCreateCart(userId);

    List<CartItem> cartItems =
        cartItemRepository.findByCartId(cart.getId());

    List<Item> result = new ArrayList<>();
    for (CartItem ci : cartItems) {
      ProductDTO product = productService.findById(ci.getProductId());
      result.add(new Item(product, ci.getQuantity()));
    }
    return result;
  }

  // ======================
  // カート追加
  // ======================
  public void add(Long userId, Integer productId, Integer quantity) {
    if (quantity == null || quantity <= 0) return;

    Cart cart = getOrCreateCart(userId);

    CartItem item =
        cartItemRepository
            .findByCartIdAndProductId(cart.getId(), productId)
            .orElse(null);

    if (item != null) {
      item.setQuantity(item.getQuantity() + quantity);
    } else {
      cartItemRepository.save(
          new CartItem(cart.getId(), productId, quantity)
      );
    }
  }

  // ======================
  // 数量変更
  // ======================
  public void updateQuantity(Long userId,
                             Integer productId,
                             Integer quantity) {

    Cart cart = getOrCreateCart(userId);

    CartItem item =
        cartItemRepository
            .findByCartIdAndProductId(cart.getId(), productId)
            .orElse(null);

    if (item == null) return;

    if (quantity <= 0) {
      cartItemRepository.delete(item);
    } else {
      item.setQuantity(quantity);
    }
  }

  // ======================
  // 商品削除
  // ======================
  public void remove(Long userId, Integer productId) {
    Cart cart = getOrCreateCart(userId);

    cartItemRepository
        .findByCartIdAndProductId(cart.getId(), productId)
        .ifPresent(cartItemRepository::delete);
  }

  // ======================
  // 合計金額計算
  // ======================
  public int calcTotal(List<Item> items) {
    int total = 0;
    for (Item item : items) {
      total += item.getProduct().getPrice() * item.getCount();
    }
    return total;
  }
}
