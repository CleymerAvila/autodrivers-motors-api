package com.autodrivers.motors.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CrearClienteDTO(
        @NotBlank
        String nombreCompleto,
        @NotBlank
        @Size(min = 10, max = 12)
        String dni,
        @Size(min = 10, max = 12)
        String telefono,
        @NotBlank
        String direccion,
        @NotBlank
        @Email
        String correoElectronico
) {
}
