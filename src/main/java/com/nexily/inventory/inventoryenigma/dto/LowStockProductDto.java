package com.nexily.inventory.inventoryenigma.dto;

import lombok.Data;

@Data
public class LowStockProductDto {

    private Long id;
    private String name;
    private String sku;
    private Integer stockQty;
}
