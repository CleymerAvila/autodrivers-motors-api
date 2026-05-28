package com.autodrivers.motors.dto.mantenimiento;

import com.autodrivers.motors.domain.model.MantenimientoEstado;
import com.autodrivers.motors.domain.model.MantenimientoTipo;
import com.autodrivers.motors.domain.model.Vehiculo;

import java.time.LocalDateTime;

public record CrearMantenimientoDTO(
        Vehiculo vehiculo,
        MantenimientoTipo mantenimientoTipo,
        String descripcion,
        LocalDateTime fechaServicio,
        MantenimientoEstado mantenimientoEstado,
        double costoRepuesto,
        double costoManoObra,
        double costoTotal
) {
}
