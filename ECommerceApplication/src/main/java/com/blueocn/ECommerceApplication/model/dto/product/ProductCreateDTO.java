package com.blueocn.ECommerceApplication.model.dto.product;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class ProductCreateDTO {

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @NotBlank
    @Size(min = 2, max = 355)
    private String description;

    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax("1000000.0")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal price;

    @Min(0)
    @Max(10000)
    private int stock;

    public ProductCreateDTO() {
    }

    public ProductCreateDTO(String name, String description, BigDecimal price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
