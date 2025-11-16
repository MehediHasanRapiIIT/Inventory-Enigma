package com.nexily.inventory.inventoryenigma.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductDto {

    private Long id;

    @NotNull(message = "Product name is required")
    private String name;

    @NotNull(message = "Category is required")
    private String category;

    private String sku;

    @NotNull(message = "Purchase price is required")
    @DecimalMin(value = "0.01", message = "Purchase price must be greater than 0")
    private BigDecimal purchasePrice;

    @NotNull(message = "Sell price is required")
    @DecimalMin(value = "0.01", message = "Sell price must be greater than 0")
    private BigDecimal sellPrice;

    private Integer stockQty;
    private LocalDateTime createdAt;



}
