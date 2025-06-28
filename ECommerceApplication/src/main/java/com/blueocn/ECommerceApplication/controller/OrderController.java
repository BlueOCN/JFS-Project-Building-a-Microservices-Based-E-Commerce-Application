package com.blueocn.ECommerceApplication.controller;

import com.blueocn.ECommerceApplication.model.dto.order.OrderCreateDTO;
import com.blueocn.ECommerceApplication.model.dto.order.OrderDTO;
import com.blueocn.ECommerceApplication.model.dto.order.OrderUpdateDTO;
import com.blueocn.ECommerceApplication.service.order.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @Operation(summary = "Create order")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody @Valid OrderCreateDTO newOrder) {
        // Create Resource
        OrderDTO createdOrder = orderService.createOrder(newOrder);

        // Create Location
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdOrder.getOrderId())
                .toUri();

        return ResponseEntity.created(location).body(createdOrder);
    }

    @GetMapping
    @Operation(summary = "Retrieve all orders")
    public ResponseEntity<List<OrderDTO>> getOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update order by ID")
    public ResponseEntity<OrderDTO> updateOrderById(@PathVariable(name = "id") Long id, @RequestBody @Valid OrderUpdateDTO updatedOrder) {
        return ResponseEntity.ok(orderService.updateOrderById(id, updatedOrder));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete order by ID")
    public ResponseEntity<String> deleteOrderById(@PathVariable(name = "id") Long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.ok().build();
    }
}
