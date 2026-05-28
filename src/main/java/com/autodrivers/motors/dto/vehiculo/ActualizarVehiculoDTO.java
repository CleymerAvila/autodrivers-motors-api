package com.autodrivers.motors.dto.vehiculo;

import com.autodrivers.motors.domain.model.EstadoVehiculo;
import jakarta.validation.constraints.Min;


public record ActualizarVehiculoDTO(
        String marca,
        String modelo,
        String color,
        @Min(0)
        double precio,
        EstadoVehiculo estado
) {
}
