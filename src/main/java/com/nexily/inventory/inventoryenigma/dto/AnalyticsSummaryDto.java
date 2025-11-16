package com.nexily.inventory.inventoryenigma.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class AnalyticsSummaryDto {

    private Long totalProducts;

    private BigDecimal stockValue;

    private Long todayInvoices;

    private BigDecimal todayRevenue;
}
