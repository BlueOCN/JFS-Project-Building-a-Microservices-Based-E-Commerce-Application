package com.blueocn.ECommerceApplication.model.entity;

import com.blueocn.ECommerceApplication.model.dto.payment.PaymentStatus;
import com.blueocn.ECommerceApplication.model.dto.payment.PaymentMethod;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "payments")
public class PaymentEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "payment_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name = "payment_magnitude", nullable = false, precision = 12, scale = 2)
    private BigDecimal paymentMagnitude;

    @Column(name = "payment_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "payment_date", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime paymentDate;

    public PaymentEntity() {
    }

    public PaymentEntity(Long orderId, PaymentMethod paymentMethod, BigDecimal paymentMagnitude, PaymentStatus paymentStatus) {
        this.orderId = orderId;
        this.paymentMethod = paymentMethod;
        this.paymentMagnitude = paymentMagnitude;
        this.paymentStatus = paymentStatus;
    }

    public Long getPaymentId() {
        return paymentId;
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PaymentEntity that)) return false;
        return Objects.equals(paymentId, that.paymentId) && Objects.equals(orderId, that.orderId) && paymentMethod == that.paymentMethod && Objects.equals(paymentMagnitude, that.paymentMagnitude) && paymentStatus == that.paymentStatus && Objects.equals(paymentDate, that.paymentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentId, orderId, paymentMethod, paymentMagnitude, paymentStatus, paymentDate);
    }

    @Override
    public String toString() {
        return "PaymentEntity{" +
                "paymentId=" + paymentId +
                ", orderId=" + orderId +
                ", paymentMethod=" + paymentMethod +
                ", paymentMagnitude=" + paymentMagnitude +
                ", paymentStatus=" + paymentStatus +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
