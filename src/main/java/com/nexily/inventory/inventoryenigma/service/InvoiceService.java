package com.nexily.inventory.inventoryenigma.service;


import com.nexily.inventory.inventoryenigma.dto.InvoiceDto;
import com.nexily.inventory.inventoryenigma.dto.InvoiceItemDto;
import com.nexily.inventory.inventoryenigma.entity.Invoice;
import com.nexily.inventory.inventoryenigma.entity.InvoiceItem;
import com.nexily.inventory.inventoryenigma.entity.Product;
import com.nexily.inventory.inventoryenigma.entity.StockLog;
import com.nexily.inventory.inventoryenigma.exception.InsufficientStockException;
import com.nexily.inventory.inventoryenigma.exception.ProductNotFoundException;
import com.nexily.inventory.inventoryenigma.repository.InvoiceItemRepository;
import com.nexily.inventory.inventoryenigma.repository.InvoiceRepository;
import com.nexily.inventory.inventoryenigma.repository.ProductRepository;
import com.nexily.inventory.inventoryenigma.repository.StockLogRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final StockLogRepository stockLogRepository;
    private final ProductRepository productRepository;

    @Transactional
    public InvoiceDto createInvoice(InvoiceDto invoiceDto){
        for(InvoiceItemDto itemDto: invoiceDto.getItems()){
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + itemDto.getProductId()));

            if(product.getStockQty()<itemDto.getQty()){
                throw new InsufficientStockException("Insufficient stock for product: " + product.getName()+". Available: "+product.getStockQty()+", Requested: "+itemDto.getQty());

            }

        }

        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber(generateInvoiceNumber());

        invoice.setTotalPrice(BigDecimal.ZERO);

        BigDecimal totalPrice = BigDecimal.ZERO;

        for(InvoiceItemDto itemDto : invoiceDto.getItems()){
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(()-> new ProductNotFoundException(
                            "Product Not found with id: "+itemDto.getProductId()
                    ));

            product.setStockQty(product.getStockQty()-itemDto.getQty());
            productRepository.save(product);

            logStockChange(product, "SALE", itemDto.getQty());

            InvoiceItem item = new InvoiceItem();
            item.setInvoice(invoice);
            item.setProduct(product);
            item.setQty(itemDto.getQty());
            item.setUnitPrice(product.getSellingPrice());
            invoice.getItems().add(item);

            BigDecimal itemTotal = product.getSellingPrice()
                    .multiply(BigDecimal.valueOf(itemDto.getQty()));
            totalPrice = totalPrice.add(itemTotal);

            log.info("Invoice item added: Product {} - {} units at {} each",
                    product.getName(), itemDto.getQty(), product.getSellingPrice());


        }

        invoice.setTotalPrice(totalPrice);
        Invoice saved = invoiceRepository.save(invoice);

        return toDto(saved);
    }

    @Transactional(readOnly = true)
    public List<InvoiceDto> getAllInvoices(){
        return invoiceRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public InvoiceDto getInvoiceById(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with id: " + id));
        return toDto(invoice);
    }

    private String generateInvoiceNumber(){
        LocalDateTime now = LocalDateTime.now();

        String datePart = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        long count = invoiceRepository.count()+1;

        String sequecePart = String.format("%04d", count);

        return "INV-"+ datePart+"-"+sequecePart;
    }

    private void logStockChange(Product product, String changeType, Integer quantity){
        StockLog stockLog = new StockLog();
        stockLog.setProduct(product);
        stockLog.setChangeType(changeType);
        stockLog.setQuantity(quantity);
        stockLogRepository.save(stockLog);
    }

    private InvoiceDto toDto(Invoice invoice){
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setId(invoice.getId());
        invoiceDto.setInvoiceNumber(invoice.getInvoiceNumber());
        invoiceDto.setTotalPrice(invoice.getTotalPrice());
        invoiceDto.setCreatedAt(invoice.getCreatedAt());

        List<InvoiceItemDto> itemDtos = invoice.getItems().stream()
                .map(item->{
                    InvoiceItemDto itemDto = new InvoiceItemDto();
                    itemDto.setId(item.getId());
                    itemDto.setProductId(item.getProduct().getId());
                    itemDto.setProductName(item.getProduct().getName());
                    itemDto.setQty(item.getQty());
                    itemDto.setUnitPrice(item.getUnitPrice());
                    return itemDto;
                }).toList();

        invoiceDto.setItems(itemDtos);

        return invoiceDto;
    }
}
