package com.blueocn.ECommerceApplication.model.dto.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Partial update DTO for Payment")
public class PaymentPatchDTO {

    @Schema(description = "ID of the related order")
    private Long orderId;

    @Schema(description = "Selected payment method")
    private PaymentMethod paymentMethod;

    @DecimalMin(value = "1", inclusive = true, message = "Payment must be greater that zero.")
    @DecimalMax(value = "100000", inclusive = true, message = "Payment must be less than 100000.")
    @Schema(description = "Amount paid")
    private BigDecimal paymentMagnitude;

    public PaymentPatchDTO() {
    }

    public PaymentPatchDTO(Long orderId, PaymentMethod paymentMethod, BigDecimal paymentMagnitude) {
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PaymentPatchDTO that)) return false;
        return Objects.equals(orderId, that.orderId) && paymentMethod == that.paymentMethod && Objects.equals(paymentMagnitude, that.paymentMagnitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, paymentMethod, paymentMagnitude);
    }

    @Override
    public String toString() {
        return "PaymentPatchDTO{" +
                "orderId=" + orderId +
                ", paymentMethod=" + paymentMethod +
                ", paymentMagnitude=" + paymentMagnitude +
                '}';
    }
}
