package com.nexily.inventory.inventoryenigma.controller;


import com.nexily.inventory.inventoryenigma.dto.AnalyticsSummaryDto;
import com.nexily.inventory.inventoryenigma.dto.LowStockProductDto;
import com.nexily.inventory.inventoryenigma.service.AnalyticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/analytics")
@RequiredArgsConstructor
@Tag(name="Analytics", description = "Dashboard- Analytics Endpoints")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("/summary")
    @Operation(summary = "Get analytics summary",
            description = "Total products, stock value, today's invoices and revenue")
    public ResponseEntity<AnalyticsSummaryDto> getSummary(){
        AnalyticsSummaryDto summary = analyticsService.getSummary();
        return ResponseEntity.ok(summary);
    }

    @GetMapping("/low-stock")
    @Operation(summary = "Get low stock products", description = "Products with stock < 10")
    public ResponseEntity<List<LowStockProductDto>> getLowStockProducts(){
        List<LowStockProductDto> lowStock = analyticsService.getLowStockProducts();
        return ResponseEntity.ok(lowStock);
    }
}
