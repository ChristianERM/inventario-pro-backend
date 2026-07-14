package com.portafolio.inventariopro.service.impl;

import com.portafolio.inventariopro.dto.DashboardSummaryResponse;
import com.portafolio.inventariopro.repository.CategoryRepository;
import com.portafolio.inventariopro.repository.ProductRepository;
import com.portafolio.inventariopro.repository.StockMovementRepository;
import com.portafolio.inventariopro.repository.SupplierRepository;
import com.portafolio.inventariopro.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;
    private final StockMovementRepository stockMovementRepository;

    @Override
    public DashboardSummaryResponse getSummary() {
        return DashboardSummaryResponse.builder()
                .totalProducts(productRepository.countByActiveTrue())
                .totalCategories(categoryRepository.countByActiveTrue())
                .totalSuppliers(supplierRepository.countByActiveTrue())
                .lowStockProducts(productRepository.countLowStockProducts())
                .totalStockMovements(stockMovementRepository.count())
                .build();
    }
}