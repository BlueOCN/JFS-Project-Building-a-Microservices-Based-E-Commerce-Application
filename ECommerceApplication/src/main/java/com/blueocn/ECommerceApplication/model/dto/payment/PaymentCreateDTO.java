package com.blueocn.ECommerceApplication.model.dto.payment;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class PaymentCreateDTO {

    @NotNull
    private Long orderId;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    @DecimalMin(value = "1", inclusive = true, message = "Payment must be greater that zero.")
    @DecimalMax(value = "100000", inclusive = true, message = "Payment must be less than 100000.")
    private BigDecimal paymentMagnitude;

    public PaymentCreateDTO() {
    }

    public PaymentCreateDTO(Long orderId, PaymentMethod paymentMethod, BigDecimal paymentMagnitude) {
        this.orderId = orderId;
        this.paymentMethod = paymentMethod;
        this.paymentMagnitude = paymentMagnitude;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getPaymentMagnitude() {
        return paymentMagnitude;
    }

    public void setPaymentMagnitude(BigDecimal paymentMagnitude) {
        this.paymentMagnitude = paymentMagnitude;
    }
}
