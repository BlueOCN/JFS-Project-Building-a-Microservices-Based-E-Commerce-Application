package com.blueocn.ECommerceApplication.model.mapper;

import com.blueocn.ECommerceApplication.model.dto.payment.PaymentDTO;
import com.blueocn.ECommerceApplication.model.entity.PaymentEntity;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public PaymentDTO PaymentEntityToPaymentDTO(PaymentEntity paymentEntity) {
        return new PaymentDTO(paymentEntity.getPaymentId(), paymentEntity.getOrderId(), paymentEntity.getPaymentMethod(), paymentEntity.getPaymentMagnitude(), paymentEntity.getPaymentStatus(), paymentEntity.getPaymentDate());
    }
}
