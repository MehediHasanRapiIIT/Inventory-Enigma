package com.nexily.inventory.inventoryenigma.repository;

import com.nexily.inventory.inventoryenigma.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
