package com.blueocn.ECommerceApplication.model.repository.cart;

import com.blueocn.ECommerceApplication.model.entity.cart.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {
    List<CartEntity> findAllByProfileId(Long profileId);
}
