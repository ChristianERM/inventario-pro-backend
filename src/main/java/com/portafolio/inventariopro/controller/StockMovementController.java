package com.portafolio.inventariopro.controller;

import com.portafolio.inventariopro.dto.StockEntryRequest;
import com.portafolio.inventariopro.dto.StockMovementResponse;
import com.portafolio.inventariopro.service.StockMovementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.portafolio.inventariopro.dto.StockOutputRequest;

import java.util.List;

@RestController
@RequestMapping("/api/stock-movements")
@RequiredArgsConstructor
public class StockMovementController {

    private final StockMovementService stockMovementService;

    @PostMapping("/entries")
    @ResponseStatus(HttpStatus.CREATED)
    public StockMovementResponse registerEntry(@Valid @RequestBody StockEntryRequest request) {
        return stockMovementService.registerEntry(request);
    }

    @PostMapping("/outputs")
@ResponseStatus(HttpStatus.CREATED)
public StockMovementResponse registerOutput(@Valid @RequestBody StockOutputRequest request) {
    return stockMovementService.registerOutput(request);
}

    @GetMapping
    public List<StockMovementResponse> findAll() {
        return stockMovementService.findAll();
    }

    @GetMapping("/product/{productId}")
    public List<StockMovementResponse> findByProductId(@PathVariable Long productId) {
        return stockMovementService.findByProductId(productId);
    }
}