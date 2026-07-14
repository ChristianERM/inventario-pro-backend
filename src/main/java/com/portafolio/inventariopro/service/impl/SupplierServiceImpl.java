package com.portafolio.inventariopro.service.impl;

import com.portafolio.inventariopro.dto.SupplierRequest;
import com.portafolio.inventariopro.dto.SupplierResponse;
import com.portafolio.inventariopro.entity.Supplier;
import com.portafolio.inventariopro.exception.DuplicateResourceException;
import com.portafolio.inventariopro.exception.ResourceNotFoundException;
import com.portafolio.inventariopro.repository.SupplierRepository;
import com.portafolio.inventariopro.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Override
    public List<SupplierResponse> findAll() {
        return supplierRepository.findByActiveTrue()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public SupplierResponse findById(Long id) {
        Supplier supplier = supplierRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado"));

        return toResponse(supplier);
    }

    @Override
    public SupplierResponse create(SupplierRequest request) {
        if (supplierRepository.existsByDocumentNumberAndActiveTrue(request.getDocumentNumber())) {
            throw new DuplicateResourceException("Ya existe un proveedor activo con ese número de documento");
        }

        Supplier supplier = Supplier.builder()
                .businessName(request.getBusinessName())
                .documentNumber(request.getDocumentNumber())
                .contactName(request.getContactName())
                .phone(request.getPhone())
                .email(request.getEmail())
                .address(request.getAddress())
                .active(true)
                .build();

        Supplier savedSupplier = supplierRepository.save(supplier);

        return toResponse(savedSupplier);
    }

    @Override
    public SupplierResponse update(Long id, SupplierRequest request) {
        Supplier supplier = supplierRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado"));

        supplier.setBusinessName(request.getBusinessName());
        supplier.setDocumentNumber(request.getDocumentNumber());
        supplier.setContactName(request.getContactName());
        supplier.setPhone(request.getPhone());
        supplier.setEmail(request.getEmail());
        supplier.setAddress(request.getAddress());

        Supplier updatedSupplier = supplierRepository.save(supplier);

        return toResponse(updatedSupplier);
    }

    @Override
    public void delete(Long id) {
        Supplier supplier = supplierRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado"));

        supplier.setActive(false);
        supplierRepository.save(supplier);
    }

    @Override
    public SupplierResponse restore(Long id) {
        Supplier supplier = supplierRepository.findByIdAndActiveFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor inactivo no encontrado"));

        supplier.setActive(true);
        Supplier restoredSupplier = supplierRepository.save(supplier);

        return toResponse(restoredSupplier);
    }

    private SupplierResponse toResponse(Supplier supplier) {
        return SupplierResponse.builder()
                .id(supplier.getId())
                .businessName(supplier.getBusinessName())
                .documentNumber(supplier.getDocumentNumber())
                .contactName(supplier.getContactName())
                .phone(supplier.getPhone())
                .email(supplier.getEmail())
                .address(supplier.getAddress())
                .active(supplier.getActive())
                .createdAt(supplier.getCreatedAt())
                .updatedAt(supplier.getUpdatedAt())
                .build();
    }
}