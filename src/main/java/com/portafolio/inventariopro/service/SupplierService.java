package com.portafolio.inventariopro.service;

import com.portafolio.inventariopro.dto.SupplierRequest;
import com.portafolio.inventariopro.dto.SupplierResponse;

import java.util.List;

public interface SupplierService {

    List<SupplierResponse> findAll();

    SupplierResponse findById(Long id);

    SupplierResponse create(SupplierRequest request);

    SupplierResponse update(Long id, SupplierRequest request);

    void delete(Long id);

    SupplierResponse restore(Long id);
}