package com.portafolio.inventariopro.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class StockEntryRequest {

    @NotNull(message = "El producto es obligatorio")
    private Long productId;

    @NotNull(message = "El proveedor es obligatorio")
    private Long supplierId;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer quantity;

    @NotNull(message = "El precio unitario es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El precio unitario no puede ser negativo")
    private BigDecimal unitPrice;

    @Size(max = 300, message = "La observación no debe superar los 300 caracteres")
    private String observation;
}