package com.blueocn.ECommerceApplication.service;

import com.blueocn.ECommerceApplication.model.entity.cart.CartEntity;
import com.blueocn.ECommerceApplication.model.entity.cart.CartProductEntity;
import com.blueocn.ECommerceApplication.model.repository.cart.CartProductRepository;
import com.blueocn.ECommerceApplication.model.repository.cart.CartRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartProductRepository cartProductRepository;

    CartService(CartRepository cartRepository, CartProductRepository cartProductRepository) {
        this.cartRepository = cartRepository;
        this.cartProductRepository = cartProductRepository;
    }

    // Create cart
    public CartEntity createCart(Long profileId) {
        return cartRepository.save(new CartEntity(LocalDateTime.now(), profileId));
    }

    // Add cart product to cart
    public CartProductEntity createCartProduct(Long cartId, Long productId, Integer quantity) {
        if (!cartRepository.existsById(cartId)) throw new RuntimeException("Cart not found.");
        CartProductEntity cartProduct = new CartProductEntity(cartId, productId, quantity);
        return cartProductRepository.save(cartProduct);
    }

    // Add cart products to cart

    // Read cart
    public CartEntity findCartById(Long id){
        CartEntity cart = cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
        return cart;
    }

    // Find all carts by profile id
    public List<CartEntity> findAllCartsByProfileId(Long id) {
        return cartRepository.findAllByProfileId(id);
    }

    // Read all carts
    public List<CartEntity> findAllCarts() {
        return cartRepository.findAll();
    }

    // Find cart by ID and retrieve products
    public List<CartProductEntity> findProductsByCartId(Long id) {
        CartEntity cart = findCartById(id);
        return cartProductRepository.findAllByCartId(cart.getCartId());
    }

    public List<CartProductEntity> findProductByCartIdAndProductId(Long cartId, Long productId) {
        return cartProductRepository.findAllByCartIdAndProductId(cartId, productId);
    }

}
