package com.portafolio.inventariopro.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequest {

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(max = 150, message = "El nombre no debe superar los 150 caracteres")
    private String name;

    @Size(max = 500, message = "La descripción no debe superar los 500 caracteres")
    private String description;

    @NotBlank(message = "El SKU es obligatorio")
    @Size(max = 50, message = "El SKU no debe superar los 50 caracteres")
    private String sku;

    @NotBlank(message = "La unidad es obligatoria")
    @Size(max = 30, message = "La unidad no debe superar los 30 caracteres")
    private String unit;

    @NotNull(message = "El stock mínimo es obligatorio")
    @Min(value = 0, message = "El stock mínimo no puede ser negativo")
    private Integer minimumStock;

    @NotNull(message = "El precio de compra es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El precio de compra no puede ser negativo")
    private BigDecimal purchasePrice;

    @NotNull(message = "La categoría es obligatoria")
    private Long categoryId;
}