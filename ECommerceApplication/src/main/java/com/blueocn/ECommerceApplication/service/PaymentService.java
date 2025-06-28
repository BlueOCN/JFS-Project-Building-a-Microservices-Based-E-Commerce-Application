package com.blueocn.ECommerceApplication.service;

import com.blueocn.ECommerceApplication.model.dto.payment.PaymentCreateDTO;
import com.blueocn.ECommerceApplication.model.dto.payment.PaymentDTO;
import com.blueocn.ECommerceApplication.model.dto.payment.PaymentStatus;
import com.blueocn.ECommerceApplication.model.dto.payment.PaymentUpdateDTO;
import com.blueocn.ECommerceApplication.model.entity.PaymentEntity;
import com.blueocn.ECommerceApplication.model.mapper.PaymentMapper;
import com.blueocn.ECommerceApplication.model.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentService(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    public PaymentDTO createPayment(PaymentCreateDTO newPayment) {
        PaymentEntity createdPayment = paymentRepository.save(new PaymentEntity(
                newPayment.getOrderId(),
                newPayment.getPaymentMethod(),
                newPayment.getPaymentMagnitude(),
                PaymentStatus.PROCESSING
        ));
        return paymentMapper.PaymentEntityToPaymentDTO(createdPayment);
    }

    public List<PaymentDTO> readAllPayments(){
        return paymentRepository.findAll()
                .stream().map(paymentMapper::PaymentEntityToPaymentDTO)
                .toList();
    }

    public PaymentDTO readPaymentById(Long id) {
        return  paymentRepository.findById(id)
                .map(paymentMapper::PaymentEntityToPaymentDTO)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    public PaymentDTO updatePaymentById(Long id, PaymentUpdateDTO updatedPayment) {
        return paymentRepository.findById(id).map(storedPayment -> {
            storedPayment.setOrderId(updatedPayment.getOrderId());
            storedPayment.setPaymentMethod(updatedPayment.getPaymentMethod());
            storedPayment.setPaymentMagnitude(updatedPayment.getPaymentMagnitude());
            storedPayment.setPaymentStatus(PaymentStatus.PROCESSING);
            PaymentEntity updatedPaymentEntity = paymentRepository.save(storedPayment);
            return paymentMapper.PaymentEntityToPaymentDTO(updatedPaymentEntity);
        }).orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    public void deletePaymentById(Long id) {
        paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
        paymentRepository.deleteById(id);
    }
}
