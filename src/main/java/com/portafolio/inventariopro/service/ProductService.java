package com.portafolio.inventariopro.service;

import com.portafolio.inventariopro.dto.ProductRequest;
import com.portafolio.inventariopro.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    List<ProductResponse> findAll();

    ProductResponse findById(Long id);

    ProductResponse create(ProductRequest request);

    ProductResponse update(Long id, ProductRequest request);

    void delete(Long id);

    ProductResponse restore(Long id);
}