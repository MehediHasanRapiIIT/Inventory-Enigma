package com.nexily.inventory.inventoryenigma.repository;

import com.nexily.inventory.inventoryenigma.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query("SELECT i FROM Invoice i WHERE i.createdAt BETWEEN :startDate AND :endDate")
    List<Invoice> findTodayInvoices(@Param("startDate")LocalDateTime startDate, @Param("endDate")LocalDateTime endDate);
}