package com.portafolio.inventariopro.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DashboardSummaryResponse {

    private long totalProducts;
    private long totalCategories;
    private long totalSuppliers;
    private long lowStockProducts;
    private long totalStockMovements;
}