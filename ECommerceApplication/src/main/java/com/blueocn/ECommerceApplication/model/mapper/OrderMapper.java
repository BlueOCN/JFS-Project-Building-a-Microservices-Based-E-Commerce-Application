package com.blueocn.ECommerceApplication.model.mapper;

import com.blueocn.ECommerceApplication.model.dto.order.CreateOrderDTO;
import com.blueocn.ECommerceApplication.model.dto.order.OrderDTO;
import com.blueocn.ECommerceApplication.model.entity.OrderEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OrderMapper {

    public OrderDTO toDTO(OrderEntity entity) {
        return new OrderDTO(entity.getOrderId(), entity.getUserId(), entity.getOrderStatus(), entity.getCreatedAt(), entity.getUpdatedAt());
    }

//    public OrderEntity CDTOtoOE(CreateOrderDTO newOrder) {
//        return new OrderEntity(
//                newOrder.getUserId(),
//                newOrder.getOrderStatus(),
//                LocalDateTime.now(),
//                LocalDateTime.now(),
//                newOrder.getOrderProducts()
//        );
//    }
}
