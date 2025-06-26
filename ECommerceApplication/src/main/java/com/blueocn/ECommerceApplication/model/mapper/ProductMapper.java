package com.blueocn.ECommerceApplication.model.mapper;

import com.blueocn.ECommerceApplication.model.dto.product.CreateProductDTO;
import com.blueocn.ECommerceApplication.model.dto.product.ProductDTO;
import com.blueocn.ECommerceApplication.model.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDTO toDTO(ProductEntity entity) {
        return new ProductDTO(entity.getId(), entity.getName(), entity.getDescription(), entity.getPrice(), entity.getStock());
    }

    public ProductEntity fromDTO(CreateProductDTO dto) {
        return new ProductEntity(dto.getName(), dto.getDescription(), dto.getPrice(), dto.getStock());
    }

    public ProductEntity fromDTO(ProductDTO dto) {
        return new ProductEntity(dto.getName(), dto.getDescription(), dto.getPrice(), dto.getStock());
    }


}