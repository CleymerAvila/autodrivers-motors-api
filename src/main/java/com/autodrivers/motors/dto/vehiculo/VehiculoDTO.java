package com.autodrivers.motors.dto.vehiculo;

import com.autodrivers.motors.domain.model.EstadoVehiculo;
import com.autodrivers.motors.domain.model.Vehiculo;
import com.autodrivers.motors.dto.conversor.TasaCambio;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record VehiculoDTO(
        @NotNull
        Long vehiculoId,
        @NotBlank
        String placa,
        @Min(0)
        double precio,
        @NotBlank
        String marca,
        @NotBlank
        String modelo,
        @NotNull
        LocalDate anioFabricacion,
        @NotNull
        EstadoVehiculo estado,
        @NotBlank
        String color,
        @NotNull
        TasaCambio tasaCambioUSD,
        @NotNull
        TasaCambio tasaCambioEUR
) {

    public VehiculoDTO(Vehiculo vehiculo, TasaCambio tasaCambioUSD, TasaCambio tasaCambioEUR){
        this(
                vehiculo.getVehiculoId(), vehiculo.getPlaca(),
                vehiculo.getPrecio(), vehiculo.getMarca(),vehiculo.getModelo(),
                vehiculo.getAnioFabricacion(), vehiculo.getEstado(),
                vehiculo.getColor(),
                tasaCambioUSD,
                tasaCambioEUR
        );
    }
}
