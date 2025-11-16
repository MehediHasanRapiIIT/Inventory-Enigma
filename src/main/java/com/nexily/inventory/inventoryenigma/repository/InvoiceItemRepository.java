package com.nexily.inventory.inventoryenigma.repository;

import com.nexily.inventory.inventoryenigma.entity.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {
}