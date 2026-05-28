package com.autodrivers.motors.dto.vehiculo;

import com.autodrivers.motors.domain.model.EstadoVehiculo;
import com.autodrivers.motors.domain.model.Vehiculo;

import java.time.LocalDate;

public record VehiculoDTO(
        Long vehiculoId,
        String placa,
        double precio,
        String marca,
        String modelo,
        LocalDate anioFabricacion,
        EstadoVehiculo estado,
        String color
) {

    public VehiculoDTO(Vehiculo vehiculo){
        this(
                vehiculo.getVehiculoId(), vehiculo.getPlaca(),
                vehiculo.getPrecio(), vehiculo.getMarca(),vehiculo.getModelo(),
                vehiculo.getAnioFabricacion(), vehiculo.getEstado(),
                vehiculo.getColor()
        );
    }
}
