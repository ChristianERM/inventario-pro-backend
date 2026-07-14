package com.portafolio.inventariopro.repository;

import com.portafolio.inventariopro.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

    List<Category> findByActiveTrue();

    Optional<Category> findByIdAndActiveTrue(Long id);

    Optional<Category> findByIdAndActiveFalse(Long id);

    boolean existsByNameAndActiveTrue(String name);

    long countByActiveTrue();
}