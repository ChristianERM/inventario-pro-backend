package com.portafolio.inventariopro.service;

import com.portafolio.inventariopro.dto.StockAlertResponse;
import com.portafolio.inventariopro.entity.StockAlert;
import com.portafolio.inventariopro.enums.AlertStatus;
import com.portafolio.inventariopro.repository.StockAlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockAlertService {

    private final StockAlertRepository stockAlertRepository;

    public List<StockAlertResponse> findPendingAlerts() {
    return stockAlertRepository.findByStatusWithProductOrderByCreatedAtDesc(AlertStatus.PENDING)
        .stream()
        .map(this::toResponse)
        .toList();
    }

    private StockAlertResponse toResponse(StockAlert alert) {
        return StockAlertResponse.builder()
                .id(alert.getId())
                .type(alert.getType())
                .status(alert.getStatus())
                .message(alert.getMessage())
                .currentStock(alert.getCurrentStock())
                .minimumStock(alert.getMinimumStock())
                .productId(alert.getProduct().getId())
                .productName(alert.getProduct().getName())
                .productSku(alert.getProduct().getSku())
                .productUnit(alert.getProduct().getUnit())
                .createdAt(alert.getCreatedAt())
                .build();
    }
}