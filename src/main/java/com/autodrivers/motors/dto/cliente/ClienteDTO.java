package com.autodrivers.motors.dto.cliente;

import com.autodrivers.motors.domain.model.Cliente;

public record ClienteDTO(
        Long clienteId,
        String nombreCompleto,
        String dni,
        String telefono,
        String direccion,
        String correoElectronico
) {

    public ClienteDTO(Cliente cliente){
        this(cliente.getClienteId(), cliente.getNombreCompleto(),
                cliente.getDni(), cliente.getTelefono(),
                cliente.getDireccion(), cliente.getCorreoElectronico()
        );
    }
}
