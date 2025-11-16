package com.nexily.inventory.inventoryenigma.repository;

import com.nexily.inventory.inventoryenigma.entity.StockLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StockLogRepository extends JpaRepository<StockLog, Long> {
}