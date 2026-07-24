package com.portafolio.inventariopro.controller;

import com.portafolio.inventariopro.dto.StockAlertResponse;
import com.portafolio.inventariopro.service.StockAlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-alerts")
@RequiredArgsConstructor
public class StockAlertController {

    private final StockAlertService stockAlertService;

    @GetMapping("/pending")
    public List<StockAlertResponse> findPendingAlerts() {
        return stockAlertService.findPendingAlerts();
    }

    @PatchMapping("/{id}/resolve")
    public StockAlertResponse resolveAlert(@PathVariable Long id) {
        return stockAlertService.resolveAlert(id);
    }
}