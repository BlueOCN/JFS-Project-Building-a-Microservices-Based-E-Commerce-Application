package com.blueocn.ECommerceApplication.controller;

import com.blueocn.ECommerceApplication.model.dto.payment.PaymentCreateDTO;
import com.blueocn.ECommerceApplication.model.dto.payment.PaymentDTO;
import com.blueocn.ECommerceApplication.model.dto.payment.PaymentUpdateDTO;
import com.blueocn.ECommerceApplication.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    @Operation(summary = "Create payment")
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody @Valid PaymentCreateDTO newPayment) {

        PaymentDTO createdPayment = paymentService.createPayment(newPayment);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/{id}")
                .buildAndExpand(createdPayment.getPaymentId())
                .toUri();

        return ResponseEntity.created(location).body(createdPayment);
    }

    @GetMapping
    @Operation(summary = "Retrieve all payments")
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        return ResponseEntity.ok(paymentService.readAllPayments());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve payment by ID")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(paymentService.readPaymentById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update payment by ID")
    public ResponseEntity<PaymentDTO> updatePaymentById(@PathVariable(name = "id") Long id, @RequestBody @Valid PaymentUpdateDTO updatedPayment) {
        return ResponseEntity.ok(paymentService.updatePaymentById(id, updatedPayment));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete payment by ID")
    public ResponseEntity<?> deletePaymentById(@PathVariable(name = "id") Long id) {
        paymentService.deletePaymentById(id);
        return ResponseEntity.noContent().build();
    }

}
