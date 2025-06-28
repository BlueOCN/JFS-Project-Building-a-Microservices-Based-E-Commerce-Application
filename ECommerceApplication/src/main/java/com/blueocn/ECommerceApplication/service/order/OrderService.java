package com.blueocn.ECommerceApplication.service.order;

import com.blueocn.ECommerceApplication.model.dto.order.OrderCreateDTO;
import com.blueocn.ECommerceApplication.model.dto.order.OrderDTO;
import com.blueocn.ECommerceApplication.model.dto.order.OrderUpdateDTO;
import com.blueocn.ECommerceApplication.model.dto.product.ProductDTO;
import com.blueocn.ECommerceApplication.model.entity.OrderEntity;
import com.blueocn.ECommerceApplication.model.entity.ProductEntity;
import com.blueocn.ECommerceApplication.model.mapper.OrderMapper;
import com.blueocn.ECommerceApplication.model.repository.OrderRepository;
import com.blueocn.ECommerceApplication.model.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final WebClient productWebClient;

    OrderService(
            OrderRepository orderRepository,
            OrderMapper orderMapper,
            WebClient productWebClient
    ) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.productWebClient = productWebClient;
    }

    // Create order
//    public OrderDTO createOrder(OrderCreateDTO newOrder) {
//
//        // Extract Order Product's Ids from the New Order
//        List<Long> orderProductIds = newOrder.getOrderProducts();
//
//        // Find Order Product's Details on the DB
//        List<ProductEntity> orderProducts = productRepository.findAllById(orderProductIds);
//
//        OrderEntity newCompleteOrder = new OrderEntity(
//                newOrder.getUserId(),
//                newOrder.getOrderStatus(),
//                LocalDateTime.now(),
//                LocalDateTime.now(),
//                orderProducts
//        );
//
//        OrderEntity createdOrder = orderRepository.save(newCompleteOrder);
//        return orderMapper.toDTO(createdOrder);
//    }

    public OrderDTO createOrder(OrderCreateDTO newOrder) {

        // Extract Order Product's Ids from the New Order
        List<Long> orderProductIds = newOrder.getOrderProducts();

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/products/bulk/{ids}")
                .buildAndExpand(orderProductIds.stream().map(String::valueOf).collect(Collectors.joining(",")))
                .toUri();

        // Send GET request to Product Controller
        List<ProductEntity> matchProducts = productWebClient.get()
                .uri(location)
                .retrieve()
                .bodyToFlux(ProductEntity.class)
                .collectList()
                .block();

        if (matchProducts == null || matchProducts.isEmpty()) {
            throw new IllegalArgumentException("Failed to fetch products for given IDs");
        }

        if (matchProducts.size() != orderProductIds.size()) {
            throw new IllegalStateException("Some product IDs could not be resolved");
        }


        OrderEntity newCompleteOrder = new OrderEntity(
                newOrder.getUserId(),
                newOrder.getOrderStatus(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                matchProducts
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
    public OrderDTO updateOrderById(Long id, OrderUpdateDTO updatedOrder) {

        // Extract Order Product's Ids from the New Order
        List<Long> orderProductIds = updatedOrder.getOrderProductIds();

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/products/bulk/{ids}")
                .buildAndExpand(orderProductIds.stream().map(String::valueOf).collect(Collectors.joining(",")))
                .toUri();

        // Send GET request to Product Controller
        List<ProductEntity> matchProducts = productWebClient.get()
                .uri(location)
                .retrieve()
                .bodyToFlux(ProductEntity.class)
                .collectList()
                .block();

        return orderRepository.findById(id)
                .map(currentOrder -> {
                    currentOrder.setUserId(updatedOrder.getUserId());
                    currentOrder.setOrderStatus(updatedOrder.getOrderStatus());
                    currentOrder.setOrderProducts(matchProducts);
                    currentOrder.setUpdatedAt(LocalDateTime.now());
                    return orderMapper.toDTO(orderRepository.save(currentOrder));
                }).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    // Delete order by id
    public void deleteOrderById(Long id) {
        orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found"));
        orderRepository.deleteById(id);
    }

}
