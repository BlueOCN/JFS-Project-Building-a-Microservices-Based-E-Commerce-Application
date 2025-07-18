package com.blueocn.ECommerceApplication.model.repository;

import com.blueocn.ECommerceApplication.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
