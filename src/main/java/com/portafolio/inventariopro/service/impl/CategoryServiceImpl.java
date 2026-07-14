package com.portafolio.inventariopro.service.impl;

import com.portafolio.inventariopro.dto.CategoryRequest;
import com.portafolio.inventariopro.dto.CategoryResponse;
import com.portafolio.inventariopro.entity.Category;
import com.portafolio.inventariopro.exception.DuplicateResourceException;
import com.portafolio.inventariopro.exception.ResourceNotFoundException;
import com.portafolio.inventariopro.repository.CategoryRepository;
import com.portafolio.inventariopro.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> findAll() {
        return categoryRepository.findByActiveTrue()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public CategoryResponse findById(Long id) {
        Category category = categoryRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));

        return toResponse(category);
    }

    @Override
    public CategoryResponse create(CategoryRequest request) {
        if (categoryRepository.existsByNameAndActiveTrue(request.getName())) {
            throw new DuplicateResourceException("Ya existe una categoría activa con ese nombre");
        }

        Category category = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .active(true)
                .build();

        Category savedCategory = categoryRepository.save(category);

        return toResponse(savedCategory);
    }

    @Override
    public CategoryResponse update(Long id, CategoryRequest request) {
        Category category = categoryRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        Category updatedCategory = categoryRepository.save(category);

        return toResponse(updatedCategory);
    }

    @Override
    public void delete(Long id) {
        Category category = categoryRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));

        category.setActive(false);
        categoryRepository.save(category);
    }

    @Override
    public CategoryResponse restore(Long id) {
        Category category = categoryRepository.findByIdAndActiveFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría inactiva no encontrada"));

        category.setActive(true);
        Category restoredCategory = categoryRepository.save(category);

        return toResponse(restoredCategory);
    }

    private CategoryResponse toResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .active(category.getActive())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }
}