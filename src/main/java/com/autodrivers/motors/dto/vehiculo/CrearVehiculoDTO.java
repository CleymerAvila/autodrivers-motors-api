package com.autodrivers.motors.dto.vehiculo;

import com.autodrivers.motors.domain.model.EstadoVehiculo;

import java.time.LocalDate;

public record CrearVehiculoDTO(
        String placa,
        String marca,
        String modelo,
        String color,
        double precio,
        LocalDate anioFabricacion,
        EstadoVehiculo estado
) {
}
