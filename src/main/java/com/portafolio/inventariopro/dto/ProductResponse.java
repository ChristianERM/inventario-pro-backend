package com.portafolio.inventariopro.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private String sku;
    private String unit;
    private Integer currentStock;
    private Integer minimumStock;
    private BigDecimal purchasePrice;
    private Boolean active;

    private Long categoryId;
    private String categoryName;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}