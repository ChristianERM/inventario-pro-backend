package com.portafolio.inventariopro.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class SupplierResponse {

    private Long id;
    private String businessName;
    private String documentNumber;
    private String contactName;
    private String phone;
    private String email;
    private String address;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}