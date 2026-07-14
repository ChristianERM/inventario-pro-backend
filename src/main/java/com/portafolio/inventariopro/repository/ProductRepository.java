package com.portafolio.inventariopro.repository;

import com.portafolio.inventariopro.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByActiveTrue();

    Optional<Product> findByIdAndActiveTrue(Long id);

    Optional<Product> findByIdAndActiveFalse(Long id);

    boolean existsByNameAndActiveTrue(String name);

    boolean existsBySkuAndActiveTrue(String sku);

    long countByActiveTrue();

    @Query("SELECT COUNT(p) FROM Product p WHERE p.active = true AND p.currentStock <= p.minimumStock")
    long countLowStockProducts();
}