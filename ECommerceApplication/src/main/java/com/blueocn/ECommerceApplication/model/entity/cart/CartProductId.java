package com.blueocn.ECommerceApplication.model.entity.cart;

import java.io.Serializable;
import java.util.Objects;

public class CartProductId implements Serializable {
    private Long cartId;
    private Long productId;

    public CartProductId() {
    }

    public CartProductId(Long cartId, Long productId) {
        this.cartId = cartId;
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CartProductId that)) return false;
        return Objects.equals(cartId, that.cartId) && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, productId);
    }

    @Override
    public String toString() {
        return "CartProductId{" +
                "cartId=" + cartId +
                ", productId=" + productId +
                '}';
    }
}
