package com.autodrivers.motors.dto.cliente;

public record CrearClienteDTO(
        String nombreCompleto,
        String dni,
        String telefono,
        String direccion,
        String correoElectronico
) {
}
