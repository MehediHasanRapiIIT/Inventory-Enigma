package com.nexily.inventory.inventoryenigma.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false, unique = true)
    private String sku;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal purchasePrice;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal sellingPrice;

    @Column(nullable = false)
    private Integer stockQty=0;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StockLog> stockLogs = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();

        if(sku == null || sku.isEmpty()) {
            generatedSku();
        }
    }

    private void generatedSku() {
        String categoryPrefix = category.substring(0, Math.min(3, category.length())).toUpperCase();
        String timestamp = String.valueOf(System.currentTimeMillis());
        String timestampSuffix = timestamp.substring(timestamp.length() - 4);
        this.sku = categoryPrefix + "-" + timestampSuffix;
    }
}
