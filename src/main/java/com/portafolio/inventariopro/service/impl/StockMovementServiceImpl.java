package com.portafolio.inventariopro.service.impl;

import com.portafolio.inventariopro.dto.StockEntryRequest;
import com.portafolio.inventariopro.dto.StockMovementResponse;
import com.portafolio.inventariopro.entity.Product;
import com.portafolio.inventariopro.entity.StockMovement;
import com.portafolio.inventariopro.entity.Supplier;
import com.portafolio.inventariopro.enums.StockMovementType;
import com.portafolio.inventariopro.exception.ResourceNotFoundException;
import com.portafolio.inventariopro.repository.ProductRepository;
import com.portafolio.inventariopro.repository.StockMovementRepository;
import com.portafolio.inventariopro.repository.SupplierRepository;
import com.portafolio.inventariopro.service.StockMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.portafolio.inventariopro.dto.StockOutputRequest;
import com.portafolio.inventariopro.exception.BusinessException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StockMovementServiceImpl implements StockMovementService {

    private final StockMovementRepository stockMovementRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    @Override
    public StockMovementResponse registerEntry(StockEntryRequest request) {
        Product product = productRepository.findByIdAndActiveTrue(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

        Supplier supplier = supplierRepository.findByIdAndActiveTrue(request.getSupplierId())
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado"));

        Integer previousStock = product.getCurrentStock();
        Integer newStock = previousStock + request.getQuantity();

        product.setCurrentStock(newStock);
        productRepository.save(product);

        StockMovement movement = StockMovement.builder()
                .movementType(StockMovementType.IN)
                .product(product)
                .supplier(supplier)
                .quantity(request.getQuantity())
                .previousStock(previousStock)
                .newStock(newStock)
                .unitPrice(request.getUnitPrice())
                .observation(request.getObservation())
                .build();

        StockMovement savedMovement = stockMovementRepository.save(movement);

        return toResponse(savedMovement);
    }

    @Override
public StockMovementResponse registerOutput(StockOutputRequest request) {
    Product product = productRepository.findByIdAndActiveTrue(request.getProductId())
            .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

    Integer previousStock = product.getCurrentStock();

    if (previousStock < request.getQuantity()) {
    throw new BusinessException("Stock insuficiente. Stock actual: " + previousStock);
}

    Integer newStock = previousStock - request.getQuantity();

    product.setCurrentStock(newStock);
    productRepository.save(product);

    StockMovement movement = StockMovement.builder()
            .movementType(StockMovementType.OUT)
            .product(product)
            .supplier(null)
            .quantity(request.getQuantity())
            .previousStock(previousStock)
            .newStock(newStock)
            .unitPrice(product.getPurchasePrice())
            .observation(request.getObservation())
            .build();

    StockMovement savedMovement = stockMovementRepository.save(movement);

    return toResponse(savedMovement);
}

    @Override
    public List<StockMovementResponse> findAll() {
        return stockMovementRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public List<StockMovementResponse> findByProductId(Long productId) {
        productRepository.findByIdAndActiveTrue(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

        return stockMovementRepository.findByProductIdOrderByMovementDateDesc(productId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private StockMovementResponse toResponse(StockMovement movement) {
        return StockMovementResponse.builder()
                .id(movement.getId())
                .movementType(movement.getMovementType())
                .productId(movement.getProduct().getId())
                .productName(movement.getProduct().getName())
                .supplierId(movement.getSupplier() != null ? movement.getSupplier().getId() : null)
                .supplierName(movement.getSupplier() != null ? movement.getSupplier().getBusinessName() : null)
                .quantity(movement.getQuantity())
                .previousStock(movement.getPreviousStock())
                .newStock(movement.getNewStock())
                .unitPrice(movement.getUnitPrice())
                .observation(movement.getObservation())
                .movementDate(movement.getMovementDate())
                .build();
    }
}