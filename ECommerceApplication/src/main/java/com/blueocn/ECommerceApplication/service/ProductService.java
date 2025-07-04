package com.blueocn.ECommerceApplication.service;

import com.blueocn.ECommerceApplication.model.dto.product.ProductCreateDTO;
import com.blueocn.ECommerceApplication.model.dto.product.ProductDTO;
import com.blueocn.ECommerceApplication.model.entity.ProductEntity;
import com.blueocn.ECommerceApplication.model.mapper.ProductMapper;
import com.blueocn.ECommerceApplication.model.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }


    // Create product
    public ProductDTO createProduct(ProductCreateDTO newProduct) {
        ProductEntity savedProduct = productRepository.save(new ProductEntity(newProduct.getName(), newProduct.getDescription(), newProduct.getPrice(), newProduct.getStock()));
        return productMapper.toDTO(savedProduct);
    }

    // Create product
    public List<ProductDTO> createProducts(List<ProductCreateDTO> newProducts) {

        if (newProducts == null || newProducts.isEmpty()) {
            throw new IllegalArgumentException("Product list must not be empty.");
        }

        List<ProductEntity> entitiesToSave = newProducts.stream()
                .map(productMapper::fromDTO)
                .toList();

        List<ProductEntity> savedEntities = productRepository.saveAll(entitiesToSave);
        return savedEntities.stream().map(productMapper::toDTO).toList();
    }


    // Read all products
    public List<ProductDTO> getAllProducts(){
        return productRepository.findAll().stream().map(productMapper::toDTO).toList();
    }


    // Read product by id
    public ProductDTO getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // Read all products by id list
    public List<ProductDTO> getAllProductsByIds(List<Long> ids) {
        return productRepository.findAllById(ids)
                .stream().map(productMapper::toDTO)
                .toList();
    }

    // Find product by Name
    public ProductDTO getProductByName(String name) {
        return productRepository.findProductByName(name)
                .map(productMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // Find all product that share the same name
    public List<ProductDTO> getAllProductsByName(String name) {
        return productRepository.findAllProductsByName(name)
                .stream().map(productMapper::toDTO)
                .toList();
    }

    // Update product by id
    public ProductDTO updateProductById(Long id, ProductDTO updatedProduct) {
        return productRepository.findById(id)
                .map(currentProductEntity -> {
                    currentProductEntity.setName(updatedProduct.getName());
                    currentProductEntity.setDescription(updatedProduct.getDescription());
                    currentProductEntity.setPrice(updatedProduct.getPrice());
                    currentProductEntity.setStock(updatedProduct.getStock());
                    ProductEntity productEntity = productRepository.save(currentProductEntity);
                    return productMapper.toDTO(productEntity);
                }).orElseThrow(() -> {
                    return new RuntimeException("Product not found");
                });
    }

    // Update products
    public List<ProductDTO> updateProductsById(List<Long> ids, List<ProductDTO> updatedProducts) {
        if (updatedProducts.size() != ids.size()) {
            throw new IllegalArgumentException("Each ID must correspond to a product update.");
        }

        List<ProductEntity> updatedEntities = new java.util.ArrayList<>();

        for (int i = 0; i < ids.size(); i++) {
            Long id = ids.get(i);
            ProductDTO dto = updatedProducts.get(i);

            ProductEntity product = productRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

            product.setName(dto.getName());
            product.setDescription(dto.getDescription());
            product.setPrice(dto.getPrice());
            product.setStock(dto.getStock());

            updatedEntities.add(product);
        }

        return productRepository.saveAll(updatedEntities).stream()
                .map(productMapper::toDTO)
                .toList();
    }



    // Delete product by id
    public void deleteProductById(Long id) {
        productRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("Product not found");
        });
        productRepository.deleteById(id);
    }

    // Delete products
    public void deleteProductsByIds(List<Long> ids) {
        List<ProductEntity> foundProducts = productRepository.findAllById(ids);

        if (foundProducts.size() != ids.size()) {
            throw new RuntimeException("Some products could not be found for deletion.");
        }

        productRepository.deleteAllById(ids);
    }

    // Check a product stock by ID
    private Boolean checkStockById(Long id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        return product.filter(productEntity -> productEntity.getStock() > 0).isPresent();
    }

    // Check a product stock by Name
    private Boolean checkStockByName(String name) {
        Optional<ProductEntity> product = productRepository.findProductByName(name);
        return product.filter(productEntity -> productEntity.getStock() > 0).isPresent();
    }

}
