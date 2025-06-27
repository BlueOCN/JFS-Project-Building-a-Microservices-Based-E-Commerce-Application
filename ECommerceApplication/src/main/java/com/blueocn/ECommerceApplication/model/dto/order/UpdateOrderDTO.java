package com.blueocn.ECommerceApplication.model.dto.order;

import com.blueocn.ECommerceApplication.model.entity.ProductEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UpdateOrderDTO {

    @NotNull(message = "User ID cannot be null")
    @Min(value = 1, message = "User ID must be greater than 0")
    private Long userId;

    @NotNull(message = "Order status cannot be null")
    private OrderStatus orderStatus;

    @NotEmpty(message = "Order's products cannot be empty")
    private List<Long> orderProductIds = new ArrayList<>();

    public UpdateOrderDTO() {
    }

    public UpdateOrderDTO(Long userId, OrderStatus orderStatus, List<Long> orderProductIds) {
        this.userId = userId;
        this.orderStatus = orderStatus;
        this.orderProductIds = orderProductIds;
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

    public List<Long> getOrderProductIds() {
        return orderProductIds;
    }

    public void setOrderProductIds(List<Long> orderProductIds) {
        this.orderProductIds = orderProductIds;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UpdateOrderDTO that)) return false;
        return Objects.equals(userId, that.userId) && orderStatus == that.orderStatus && Objects.equals(orderProductIds, that.orderProductIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, orderStatus, orderProductIds);
    }

    @Override
    public String toString() {
        return "UpdateOrderDTO{" +
                "userId=" + userId +
                ", orderStatus=" + orderStatus +
                ", orderProducts=" + orderProductIds +
                '}';
    }
}
