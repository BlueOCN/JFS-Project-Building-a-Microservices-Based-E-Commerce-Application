package com.blueocn.ECommerceApplication.model.dto.payment;


import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentDTO {

    @NotNull
    private Long paymentId;

    @NotNull
    private Long orderId;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    @DecimalMin(value = "1", inclusive = true, message = "Payment must be greater that zero.")
    @DecimalMax(value = "100000", inclusive = true, message = "Payment must be less than 100000.")
    private BigDecimal paymentMagnitude;

    @NotNull
    private PaymentStatus paymentStatus;

    @NotNull
    private LocalDateTime paymentDate;

    public PaymentDTO() {
    }

    public PaymentDTO(Long paymentId, Long orderId, PaymentMethod paymentMethod, BigDecimal paymentMagnitude, PaymentStatus paymentStatus, LocalDateTime paymentDate) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.paymentMethod = paymentMethod;
        this.paymentMagnitude = paymentMagnitude;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
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

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
}
