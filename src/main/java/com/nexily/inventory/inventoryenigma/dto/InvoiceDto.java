package com.nexily.inventory.inventoryenigma.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class InvoiceDto {
    private Long id;
    private String invoiceNumber;
    private BigDecimal totalPrice;
    private LocalDateTime createdAt;

    @NotEmpty(message = "Invoice must contain at least one item")
    @Valid
    private List<InvoiceItemDto> items;



}
