package com.blueocn.ECommerceApplication.model.dto.order;

import com.blueocn.ECommerceApplication.model.entity.ProductEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {

    @NotNull(message = "Order ID cannot be null")
    @Min(value = 1, message = "Order ID must be greater than 0")
    private Long orderId;

    @NotNull(message = "User ID cannot be null")
    @Min(value = 1, message = "User ID must be greater than 0")
    private Long userId;

    @NotNull(message = "Order status cannot be null")
    private OrderStatus orderStatus;

    @NotNull(message = "Creation time cannot be null")
    @PastOrPresent(message = "Creation time must be in the past or present")
    private LocalDateTime createdAt;

    @NotNull(message = "Update time cannot be null")
    @PastOrPresent(message = "Update time must be in the past or present")
    private LocalDateTime updatedAt;

    @NotEmpty(message = "Update time cannot be empty")
    private List<ProductEntity> orderProducts = new ArrayList<>();

    public OrderDTO() {
    }

    public OrderDTO(Long orderId, Long userId, OrderStatus orderStatus, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
