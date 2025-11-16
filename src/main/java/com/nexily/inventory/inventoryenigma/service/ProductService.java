package com.nexily.inventory.inventoryenigma.service;


import com.nexily.inventory.inventoryenigma.dto.ProductDto;
import com.nexily.inventory.inventoryenigma.entity.Product;
import com.nexily.inventory.inventoryenigma.exception.ProductNotFoundException;
import com.nexily.inventory.inventoryenigma.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductDto createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setCategory(productDto.getCategory());
        product.setPurchasePrice(productDto.getPurchasePrice());
        product.setSellingPrice(productDto.getSellPrice());
        product.setStockQty(0);

        Product savedProduct = productRepository.save(product);

        return toDTO(savedProduct);
    }

    @Transactional
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product Not found with id: "+id));
        return toDTO(product);
    }

    @Transactional
    public ProductDto updateProduct(Long id, ProductDto dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        product.setName(dto.getName());
        product.setCategory(dto.getCategory());
        product.setPurchasePrice(dto.getPurchasePrice());
        product.setSellingPrice(dto.getSellPrice());

        Product updated = productRepository.save(product);
        return toDTO(updated);
    }

    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    private ProductDto toDTO(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setCategory(product.getCategory());
        productDto.setPurchasePrice(product.getPurchasePrice());
        productDto.setSellPrice(product.getSellingPrice());
        productDto.setStockQty(product.getStockQty());
        productDto.setCreatedAt(product.getCreatedDate());
        return productDto;

    }
}
