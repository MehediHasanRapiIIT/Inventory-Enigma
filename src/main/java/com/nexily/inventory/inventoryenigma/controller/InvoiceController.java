package com.nexily.inventory.inventoryenigma.controller;


import com.nexily.inventory.inventoryenigma.dto.InvoiceDto;
import com.nexily.inventory.inventoryenigma.service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/invoices")
@RequiredArgsConstructor
@Tag(name="Invoice Management", description = "Invoice operations")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping
    @Operation(summary = "Create a new invoice", description = "Create invoice with auto stock deduction")
    public ResponseEntity<InvoiceDto> createInvoice(@Valid @RequestBody InvoiceDto invoiceDto){
        InvoiceDto created = invoiceService.createInvoice(invoiceDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all invoices", description = "View all invoices")
    public ResponseEntity<List<InvoiceDto>> getAllInvoices(){
        List<InvoiceDto> invoices = invoiceService.getAllInvoices();
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get invoice by id", description = "Retrieve single invoice details")
    public ResponseEntity<InvoiceDto> getInvoiceById(@PathVariable Long id){
        InvoiceDto invoice = invoiceService.getInvoiceById(id);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }



}
