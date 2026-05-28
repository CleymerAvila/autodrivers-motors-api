package com.autodrivers.motors.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ActualizarClienteDTO(
        @NotBlank
        String nombreCompleto,
        @NotBlank
        String telefono,
        @NotBlank
        String direccion,
        @NotBlank
        @Email
        String correoElectronico
) {
}
