package com.blueocn.ECommerceApplication.model.dto.cart;


import java.util.Objects;

public class CartProductCreateDTO {

    private Long productId;

    private Integer quantity;

    public CartProductCreateDTO() {
    }

    public CartProductCreateDTO(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
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
        if (!(o instanceof CartProductCreateDTO that)) return false;
        return Objects.equals(productId, that.productId) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, quantity);
    }

    @Override
    public String toString() {
        return "CartProductCreateDTO{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
