package com.portafolio.inventariopro.service;

import com.portafolio.inventariopro.dto.CategoryRequest;
import com.portafolio.inventariopro.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> findAll();

    CategoryResponse findById(Long id);

    CategoryResponse create(CategoryRequest request);

    CategoryResponse update(Long id, CategoryRequest request);

    void delete(Long id);

    CategoryResponse restore(Long id);
}