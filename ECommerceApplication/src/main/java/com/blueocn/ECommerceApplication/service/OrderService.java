package com.blueocn.ECommerceApplication.service;

import com.blueocn.ECommerceApplication.model.dto.order.CreateOrderDTO;
import com.blueocn.ECommerceApplication.model.dto.order.OrderDTO;
import com.blueocn.ECommerceApplication.model.dto.order.UpdateOrderDTO;
import com.blueocn.ECommerceApplication.model.entity.OrderEntity;
import com.blueocn.ECommerceApplication.model.entity.ProductEntity;
import com.blueocn.ECommerceApplication.model.mapper.OrderMapper;
import com.blueocn.ECommerceApplication.model.repository.OrderRepository;
import com.blueocn.ECommerceApplication.model.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductRepository productRepository;

    OrderService(OrderRepository orderRepository, OrderMapper orderMapper, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.productRepository = productRepository;
    }

    // Create order
    public OrderDTO createOrder(CreateOrderDTO newOrder) {

        // Extract Order Product's Ids from the New Order
        List<Long> orderProductIds = newOrder.getOrderProducts();

        // Find Order Product's Details on the DB
        List<ProductEntity> orderProducts = productRepository.findAllById(orderProductIds);

        OrderEntity newCompleteOrder = new OrderEntity(
                newOrder.getUserId(),
                newOrder.getOrderStatus(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                orderProducts
        );

        OrderEntity createdOrder = orderRepository.save(newCompleteOrder);
        return orderMapper.toDTO(createdOrder);
    }

    // Get all orders
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(orderMapper::toDTO).toList();
    }

    // Get order by id
    public OrderDTO getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    // Update order by id
    public OrderDTO updateOrderById(Long id, UpdateOrderDTO updatedOrder) {

        // Extract Order Product's Ids from the New Order
        List<Long> orderProductIds = updatedOrder.getOrderProductIds();

        // Find Order Product's Details on the DB
        List<ProductEntity> orderProducts = productRepository.findAllById(orderProductIds);

        return orderRepository.findById(id)
                .map(currentOrder -> {
                    currentOrder.setUserId(updatedOrder.getUserId());
                    currentOrder.setOrderStatus(updatedOrder.getOrderStatus());
                    currentOrder.setOrderProducts(orderProducts);
                    currentOrder.setUpdatedAt(LocalDateTime.now());
                    return orderMapper.toDTO(orderRepository.save(currentOrder));
                }).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    // Delete order by id
    public void deleteOrderById(Long id) {
        orderRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("Order not found");
        });
        orderRepository.deleteById(id);
    }

}
