package com.blueocn.ECommerceApplication.model.repository;

import com.blueocn.ECommerceApplication.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
