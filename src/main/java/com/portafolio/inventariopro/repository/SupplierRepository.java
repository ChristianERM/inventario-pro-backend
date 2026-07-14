package com.portafolio.inventariopro.repository;

import com.portafolio.inventariopro.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    List<Supplier> findByActiveTrue();

    Optional<Supplier> findByIdAndActiveTrue(Long id);

    Optional<Supplier> findByIdAndActiveFalse(Long id);

    boolean existsByDocumentNumberAndActiveTrue(String documentNumber);

    long countByActiveTrue();
}