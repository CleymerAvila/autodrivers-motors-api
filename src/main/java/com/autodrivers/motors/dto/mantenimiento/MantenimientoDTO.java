package com.autodrivers.motors.dto.mantenimiento;

import com.autodrivers.motors.domain.model.Mantenimiento;
import com.autodrivers.motors.domain.model.MantenimientoEstado;
import com.autodrivers.motors.domain.model.MantenimientoTipo;
import com.autodrivers.motors.domain.model.Vehiculo;

import java.time.LocalDateTime;

public record MantenimientoDTO(
        Long mantenimientoId,
        Vehiculo vehiculo,
        MantenimientoTipo mantenimientoTipo,
        String descripcion,
        LocalDateTime fechaServicio,
        MantenimientoEstado mantenimientoEstado,
        double costoRepuesto,
        double costoManoObra,
        double costoTotal
) {

    public MantenimientoDTO (Mantenimiento mantenimiento) {
        this(mantenimiento.getMantenimientoId(), mantenimiento.getVehiculo(),
                mantenimiento.getMantenimientoTipo(), mantenimiento.getDescripcion(),
                mantenimiento.getFechaServicio(), mantenimiento.getMantenimientoEstado(),
                mantenimiento.getCostoRepuesto(), mantenimiento.getCostoManoObra(),
                mantenimiento.getCostoTotal()
        );
    }
}
