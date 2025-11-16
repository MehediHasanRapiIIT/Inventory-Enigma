package com.nexily.inventory.inventoryenigma.service;


import com.nexily.inventory.inventoryenigma.entity.Product;
import com.nexily.inventory.inventoryenigma.entity.StockLog;
import com.nexily.inventory.inventoryenigma.exception.InsufficientStockException;
import com.nexily.inventory.inventoryenigma.exception.ProductNotFoundException;
import com.nexily.inventory.inventoryenigma.repository.ProductRepository;
import com.nexily.inventory.inventoryenigma.repository.StockLogRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockService {

    private final ProductRepository productRepository;

    private final StockLogRepository stockLogRepository;


    @Transactional
    public void addStock(Long productId, Integer quantity){
        if(quantity <= 0){
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));

        product.setStockQty(product.getStockQty() + quantity);
        productRepository.save(product);

        logStockChange(product, "ADD", quantity);
        log.info("Added {} units to product {} ({}). New stock: {}",
                quantity, product.getId(), product.getName(), product.getStockQty());
    }

    @Transactional
    public void removeStock(Long productId, Integer quantity){
        if(quantity <= 0){
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));

        if(product.getStockQty() < quantity){
            throw new InsufficientStockException("Insufficient stock for product: " + product.getName());
        }

        product.setStockQty(product.getStockQty() - quantity);
        productRepository.save(product);

        logStockChange(product, "REMOVE", quantity);
        log.info("Removed {} units from product {} ({}). New stock: {}",
                quantity, product.getId(), product.getName(), product.getStockQty());
    }


    private void logStockChange(Product product, String changeType, Integer quantity){
        StockLog stockLog = new StockLog();
        stockLog.setProduct(product);
        stockLog.setChangeType(changeType);
        stockLog.setQuantity(quantity);
        stockLogRepository.save(stockLog);
    }

}
