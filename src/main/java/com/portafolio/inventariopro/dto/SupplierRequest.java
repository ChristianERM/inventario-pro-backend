package com.portafolio.inventariopro.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierRequest {

    @NotBlank(message = "La razón social es obligatoria")
    @Size(max = 150, message = "La razón social no debe superar los 150 caracteres")
    private String businessName;

    @NotBlank(message = "El número de documento es obligatorio")
    @Size(max = 20, message = "El número de documento no debe superar los 20 caracteres")
    private String documentNumber;

    @Size(max = 100, message = "El nombre de contacto no debe superar los 100 caracteres")
    private String contactName;

    @Size(max = 20, message = "El teléfono no debe superar los 20 caracteres")
    private String phone;

    @Email(message = "El email no tiene un formato válido")
    @Size(max = 120, message = "El email no debe superar los 120 caracteres")
    private String email;

    @Size(max = 250, message = "La dirección no debe superar los 250 caracteres")
    private String address;
}