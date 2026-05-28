package com.autodrivers.motors.dto.cliente;

public record ActualizarClienteDTO(
        String nombreCompleto,
        String telefono,
        String direccion,
        String correoElectronico
) {
}
