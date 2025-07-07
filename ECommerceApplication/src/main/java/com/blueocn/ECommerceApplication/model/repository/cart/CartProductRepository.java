package com.blueocn.ECommerceApplication.model.repository.cart;

import com.blueocn.ECommerceApplication.model.entity.cart.CartEntity;
import com.blueocn.ECommerceApplication.model.entity.cart.CartProductEntity;
import com.blueocn.ECommerceApplication.model.entity.cart.CartProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartProductRepository extends JpaRepository<CartProductEntity, CartProductId> {
    List<CartProductEntity> findAllByCartId(Long cartId);
    List<CartProductEntity> findAllByCartIdAndProductId(Long cartId, Long productId);
}
