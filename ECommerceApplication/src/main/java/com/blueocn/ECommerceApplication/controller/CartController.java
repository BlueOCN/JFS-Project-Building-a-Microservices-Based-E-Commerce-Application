package com.blueocn.ECommerceApplication.controller;

import com.blueocn.ECommerceApplication.model.dto.cart.CartProductCreateDTO;
import com.blueocn.ECommerceApplication.model.entity.cart.CartEntity;
import com.blueocn.ECommerceApplication.model.entity.cart.CartProductEntity;
import com.blueocn.ECommerceApplication.model.entity.cart.CartProductId;
import com.blueocn.ECommerceApplication.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;
    private final WebClient idEncoderWebClient;

    public CartController(CartService cartService, WebClient idEncoderWebClient) {
        this.cartService = cartService;
        this.idEncoderWebClient = idEncoderWebClient;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    @SecurityRequirement(name = "basicAuth")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Create cart")
    public ResponseEntity<CartEntity> createCart(@RequestBody Long profileId) {
        CartEntity cart = cartService.createCart(profileId);

        URI encoderLocation = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/encoder/encode/{id}")
                .buildAndExpand(cart.getCartId())
                .toUri();


        String encodedId = idEncoderWebClient.get()
                .uri(encoderLocation)
                .retrieve()
                .bodyToFlux(String.class)
                .single()
                .block();

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/{id}")
                .buildAndExpand(encodedId)
                .encode()
                .toUri();

        return ResponseEntity.created(location).body(cart);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "basicAuth")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Retrieve all carts")
    public ResponseEntity<List<CartEntity>> getALLCarts(
            @RequestParam(required = false) Long profileId
    ) {
        if (profileId != null) return  ResponseEntity.ok(cartService.findAllCartsByProfileId(profileId));
        else return ResponseEntity.ok(cartService.findAllCarts());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "basicAuth")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Retrieve cart by ID")
    public CartEntity getCartById(@PathVariable(name = "id") Long id) {
        return cartService.findCartById(id);
    }

    @PostMapping("/{id}/products")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    @SecurityRequirement(name = "basicAuth")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Add product ID to cart by cart ID")
    public ResponseEntity<CartProductEntity> addProductIdToCartByCartId(
            @PathVariable(name = "id") Long cartId,
            @RequestBody CartProductCreateDTO newProduct
    ) {
        CartProductEntity product = cartService.createCartProduct(
                cartId, newProduct.getProductId(), newProduct.getQuantity()
        );

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{productId}")
                .buildAndExpand(product.getProductId())
                .toUri();

        return ResponseEntity.created(location).body(product);
    }

    @GetMapping("/{id}/products")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "basicAuth")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Retrieve cart products by ID")
    public ResponseEntity<List<CartProductEntity>> getProductsByCartId(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(cartService.findProductsByCartId(id));
    }

    @GetMapping("/{cartId}/products/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "basicAuth")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Retrieve product by Cart ID and Product ID")
    public ResponseEntity<List<CartProductEntity>> getProductByCartIdAndProductId(
            @PathVariable(name = "cartId") Long cartId,
            @PathVariable(name = "productId") Long productId
    ) {
        return ResponseEntity.ok(cartService.findProductByCartIdAndProductId(cartId, productId));
    }
}
