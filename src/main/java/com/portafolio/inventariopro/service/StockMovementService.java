package com.portafolio.inventariopro.service;

import com.portafolio.inventariopro.dto.StockEntryRequest;
import com.portafolio.inventariopro.dto.StockMovementResponse;
import com.portafolio.inventariopro.dto.StockOutputRequest;

import java.util.List;

public interface StockMovementService {

    StockMovementResponse registerEntry(StockEntryRequest request);

    StockMovementResponse registerOutput(StockOutputRequest request);

    List<StockMovementResponse> findAll();

    List<StockMovementResponse> findByProductId(Long productId);
}