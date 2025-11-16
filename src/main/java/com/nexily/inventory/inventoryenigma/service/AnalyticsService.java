package com.nexily.inventory.inventoryenigma.service;

import com.nexily.inventory.inventoryenigma.dto.AnalyticsSummaryDto;
import com.nexily.inventory.inventoryenigma.dto.LowStockProductDto;
import com.nexily.inventory.inventoryenigma.entity.Invoice;
import com.nexily.inventory.inventoryenigma.entity.Product;
import com.nexily.inventory.inventoryenigma.repository.InvoiceRepository;
import com.nexily.inventory.inventoryenigma.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final ProductRepository productRepository;
    private final InvoiceRepository invoiceRepository;


    @Transactional(readOnly = true)
    public AnalyticsSummaryDto getSummary(){
        long totalProducts = productRepository.count();

        List<Product> products = productRepository.findAll();
        BigDecimal stockValue = products.stream()
                .map(p->p.getPurchasePrice().multiply(BigDecimal.valueOf(p.getStockQty())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        LocalDateTime startOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);

        List<Invoice> todayInvoices = invoiceRepository.findTodayInvoices(startOfDay, endOfDay);

        long todayInvoiceCount = todayInvoices.size();
        BigDecimal todayRevenue = todayInvoices.stream()
                .map(Invoice::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        AnalyticsSummaryDto summaryDto = new AnalyticsSummaryDto();
        summaryDto.setTotalProducts(totalProducts);
        summaryDto.setStockValue(stockValue);
        summaryDto.setTodayInvoices(todayInvoiceCount);
        summaryDto.setTodayRevenue(todayRevenue);

        return summaryDto;

    }

    @Transactional(readOnly = true)
    public List<LowStockProductDto> getLowStockProducts(){
        List<Product> lowStockProducts = productRepository.findByStockQtyLessThan(10);
        return lowStockProducts.stream()
                .map(product -> {
                    LowStockProductDto dto = new LowStockProductDto();
                    dto.setId(product.getId());
                    dto.setName(product.getName());
                    dto.setSku(product.getSku());
                    dto.setStockQty(product.getStockQty());
                    return dto;
                }).toList();
    }
}
