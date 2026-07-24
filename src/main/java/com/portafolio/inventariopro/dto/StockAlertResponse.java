package com.portafolio.inventariopro.dto;

import com.portafolio.inventariopro.enums.AlertStatus;
import com.portafolio.inventariopro.enums.AlertType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class StockAlertResponse {

    private Long id;
    private AlertType type;
    private AlertStatus status;
    private String message;
    private Integer currentStock;
    private Integer minimumStock;
    private Long productId;
    private String productName;
    private String productSku;
    private String productUnit;
    private LocalDateTime createdAt;
}