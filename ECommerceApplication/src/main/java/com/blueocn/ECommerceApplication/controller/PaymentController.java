package com.blueocn.ECommerceApplication.controller;

import com.blueocn.ECommerceApplication.model.dto.payment.PaymentCreateDTO;
import com.blueocn.ECommerceApplication.model.dto.payment.PaymentDTO;
import com.blueocn.ECommerceApplication.model.dto.payment.PaymentPatchDTO;
import com.blueocn.ECommerceApplication.model.dto.payment.PaymentUpdateDTO;
import com.blueocn.ECommerceApplication.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @SecurityRequirement(name = "basicAuth")
    @SecurityRequirement(name = "bearerAuth")
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
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "basicAuth")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Retrieve all payments")
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        return ResponseEntity.ok(paymentService.readAllPayments());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "basicAuth")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Retrieve payment by ID")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(paymentService.readPaymentById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "basicAuth")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Update payment by ID")
    public ResponseEntity<PaymentDTO> updatePaymentById(@PathVariable(name = "id") Long id, @RequestBody @Valid PaymentUpdateDTO updatedPayment) {
        return ResponseEntity.ok(paymentService.updatePaymentById(id, updatedPayment));
    }

    @PatchMapping("{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @SecurityRequirement(name = "basicAuth")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Patch payment by ID")
    public ResponseEntity<PaymentDTO> patchPaymentById(@PathVariable(name = "id") Long id, @RequestBody @Valid PaymentPatchDTO patchedPayment) {
        return ResponseEntity.ok(paymentService.patchPaymentById(id, patchedPayment));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "basicAuth")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Delete payment by ID")
    public ResponseEntity<?> deletePaymentById(@PathVariable(name = "id") Long id) {
        paymentService.deletePaymentById(id);
        return ResponseEntity.noContent().build();
    }

}
