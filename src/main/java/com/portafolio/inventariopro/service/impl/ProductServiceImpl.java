package com.portafolio.inventariopro.service.impl;

import com.portafolio.inventariopro.dto.ProductRequest;
import com.portafolio.inventariopro.dto.ProductResponse;
import com.portafolio.inventariopro.entity.Category;
import com.portafolio.inventariopro.entity.Product;
import com.portafolio.inventariopro.exception.DuplicateResourceException;
import com.portafolio.inventariopro.exception.ResourceNotFoundException;
import com.portafolio.inventariopro.repository.CategoryRepository;
import com.portafolio.inventariopro.repository.ProductRepository;
import com.portafolio.inventariopro.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<ProductResponse> findAll() {
        return productRepository.findByActiveTrue()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public ProductResponse findById(Long id) {
        Product product = productRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

        return toResponse(product);
    }

    @Override
    public ProductResponse create(ProductRequest request) {
        if (productRepository.existsByNameAndActiveTrue(request.getName())) {
            throw new DuplicateResourceException("Ya existe un producto activo con ese nombre");
        }

        if (productRepository.existsBySkuAndActiveTrue(request.getSku())) {
            throw new DuplicateResourceException("Ya existe un producto activo con ese SKU");
        }

        Category category = categoryRepository.findByIdAndActiveTrue(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .sku(request.getSku())
                .unit(request.getUnit())
                .minimumStock(request.getMinimumStock())
                .purchasePrice(request.getPurchasePrice())
                .currentStock(0)
                .active(true)
                .category(category)
                .build();

        Product savedProduct = productRepository.save(product);

        return toResponse(savedProduct);
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {
        Product product = productRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

        Category category = categoryRepository.findByIdAndActiveTrue(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setSku(request.getSku());
        product.setUnit(request.getUnit());
        product.setMinimumStock(request.getMinimumStock());
        product.setPurchasePrice(request.getPurchasePrice());
        product.setCategory(category);

        Product updatedProduct = productRepository.save(product);

        return toResponse(updatedProduct);
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

        product.setActive(false);
        productRepository.save(product);
    }

    @Override
    public ProductResponse restore(Long id) {
        Product product = productRepository.findByIdAndActiveFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto inactivo no encontrado"));

        product.setActive(true);
        Product restoredProduct = productRepository.save(product);

        return toResponse(restoredProduct);
    }

    private ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .sku(product.getSku())
                .unit(product.getUnit())
                .currentStock(product.getCurrentStock())
                .minimumStock(product.getMinimumStock())
                .purchasePrice(product.getPurchasePrice())
                .active(product.getActive())
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}