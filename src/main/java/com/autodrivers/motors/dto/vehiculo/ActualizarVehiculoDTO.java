package com.autodrivers.motors.dto.vehiculo;

import com.autodrivers.motors.domain.model.EstadoVehiculo;


public record ActualizarVehiculoDTO(
        String marca,
        String modelo,
        String color,
        double precio,
        EstadoVehiculo estado
) {
}
