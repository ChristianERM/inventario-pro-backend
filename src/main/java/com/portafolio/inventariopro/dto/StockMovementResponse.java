package com.portafolio.inventariopro.dto;

import com.portafolio.inventariopro.enums.StockMovementType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class StockMovementResponse {

    private Long id;
    private StockMovementType movementType;

    private Long productId;
    private String productName;

    private Long supplierId;
    private String supplierName;

    private Integer quantity;
    private Integer previousStock;
    private Integer newStock;

    private BigDecimal unitPrice;
    private String observation;
    private LocalDateTime movementDate;
}