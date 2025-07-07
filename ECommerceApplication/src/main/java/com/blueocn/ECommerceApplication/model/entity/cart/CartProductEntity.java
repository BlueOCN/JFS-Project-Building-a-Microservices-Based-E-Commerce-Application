package com.blueocn.ECommerceApplication.model.entity.cart;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "cart_products")
@IdClass(CartProductId.class)
public class CartProductEntity {

    @Id
    @Column(name = "cart_id", nullable = false)
    private Long cartId;

    @Id
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public CartProductEntity() {
    }

    public CartProductEntity(Long cartId, Long productId, Integer quantity) {
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CartProductEntity that)) return false;
        return Objects.equals(cartId, that.cartId) && Objects.equals(productId, that.productId) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, productId, quantity);
    }

    @Override
    public String toString() {
        return "CartProductEntity{" +
                "cartId=" + cartId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
