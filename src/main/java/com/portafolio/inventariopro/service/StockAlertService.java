package com.portafolio.inventariopro.service;

import com.portafolio.inventariopro.dto.StockAlertResponse;
import com.portafolio.inventariopro.entity.StockAlert;
import com.portafolio.inventariopro.enums.AlertStatus;
import com.portafolio.inventariopro.repository.StockAlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockAlertService {

    private final StockAlertRepository stockAlertRepository;

    public List<StockAlertResponse> findPendingAlerts() {
        return stockAlertRepository
                .findByStatusWithProductOrderByCreatedAtDesc(AlertStatus.PENDING)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public StockAlertResponse notifyAlert(Long id) {
        StockAlert alert = stockAlertRepository.findByIdWithProduct(id)
                .orElseThrow(() -> new RuntimeException("Alerta no encontrada"));

        if (alert.getStatus() == AlertStatus.PENDING) {
            alert.setStatus(AlertStatus.NOTIFIED);
            stockAlertRepository.save(alert);
        }

        return toResponse(alert);
    }

    @Transactional
    public StockAlertResponse resolveAlert(Long id) {
        StockAlert alert = stockAlertRepository.findByIdWithProduct(id)
                .orElseThrow(() -> new RuntimeException("Alerta no encontrada"));

        alert.setStatus(AlertStatus.RESOLVED);
        alert.setResolvedAt(LocalDateTime.now());

        StockAlert savedAlert = stockAlertRepository.save(alert);
        return toResponse(savedAlert);
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
