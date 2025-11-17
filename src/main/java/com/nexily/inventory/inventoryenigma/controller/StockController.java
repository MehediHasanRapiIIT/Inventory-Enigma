package com.nexily.inventory.inventoryenigma.controller;


import com.nexily.inventory.inventoryenigma.dto.StockUpdateDto;
import com.nexily.inventory.inventoryenigma.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/stock")
@RequiredArgsConstructor
@Tag(name = "Stock Management", description = "Stock operations")
public class StockController {
    private final StockService stockService;


    @PostMapping("/add")
    @Operation(summary = "Add stock", description = "Increase stock quantity")
    public ResponseEntity<String> addStock(@Valid @RequestBody StockUpdateDto dto){
        stockService.addStock(dto.getProductId(), dto.getQty());
        return ResponseEntity.ok("Stock added successfully");
    }

    @PostMapping("/remove")
    @Operation(summary = "Remove stock", description = "Decrease stock quantity (never below zero)")
    public ResponseEntity<String> removeStock(@Valid @RequestBody StockUpdateDto dto){
        stockService.removeStock(dto.getProductId(), dto.getQty());
        return ResponseEntity.ok("Stock removed successfully");
    }

}
