package com.blueocn.ECommerceApplication.controller;

import com.blueocn.ECommerceApplication.model.dto.product.ProductCreateDTO;
import com.blueocn.ECommerceApplication.model.dto.product.ProductDTO;
import com.blueocn.ECommerceApplication.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "basicAuth")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Create product")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductCreateDTO newProduct) {

        // Create Resource
        ProductDTO createdProduct = productService.createProduct(newProduct);

        // Create URI
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdProduct.getId())
                .toUri();

        return ResponseEntity.created(location).body(createdProduct);
    }

    @PostMapping("/bulk")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "basicAuth")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Bulk create products")
    public ResponseEntity<?> createProducts(@RequestBody @Valid List<ProductCreateDTO> newProducts) {
        try {
            List<ProductDTO> createdProducts = productService.createProducts(newProducts);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .replacePath("/products")
                    .build()
                    .toUri();

            return ResponseEntity.created(location).body(createdProducts);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
        } catch (Exception ex) {
            throw new RuntimeException("Unexpected error during product creation", ex);
        }
    }


    @GetMapping
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    @SecurityRequirement(name = "basicAuth")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Retrieve all products")
    public ResponseEntity<List<ProductDTO>> getAllProducts(
            @RequestParam(required = false) String name
    ) {

        if (name != null) {
            return ResponseEntity.ok(productService.getAllProductsByName(name));
        } else {
            return ResponseEntity.ok(productService.getAllProducts());
        }

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    @SecurityRequirement(name = "basicAuth")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Retrieve product by ID")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/bulk/{ids}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    @SecurityRequirement(name = "basicAuth")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Retrieve products by IDs")
    public ResponseEntity<List<ProductDTO>> getProductsByIds(@PathVariable(name = "ids") List<Long> ids) {
        return ResponseEntity.ok(productService.getAllProductsByIds(ids));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "basicAuth")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Retrieve products by ID")
    public ResponseEntity<ProductDTO> updateProductById(@PathVariable(name = "id") Long id, @RequestBody @Valid ProductDTO updatedProduct) {
        return ResponseEntity.ok(productService.updateProductById(id, updatedProduct));
    }

    @PutMapping("/bulk/{ids}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "basicAuth")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Update product by ID")
    public ResponseEntity<List<ProductDTO>> updateProductsById(@PathVariable(name = "ids") List<Long> ids, @RequestBody List< @Valid ProductDTO> updatedProducts) {
        return ResponseEntity.ok(productService.updateProductsById(ids, updatedProducts));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "basicAuth")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Delete product by ID")
    public ResponseEntity<String> deleteProductById(@PathVariable(name = "id") Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/bulk/{ids}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "basicAuth")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Delete products by ID")
    public ResponseEntity<String> deleteProductsById(@PathVariable(name = "ids") List<Long> ids) {
        productService.deleteProductsByIds(ids);
        return ResponseEntity.ok().build();
    }

}
