package com.autodrivers.motors.dto.venta;

import com.autodrivers.motors.domain.model.VentaEstado;
import com.autodrivers.motors.domain.model.VentaTipo;

import java.time.LocalDateTime;

public record ActualizarVentaDTO(
        LocalDateTime fechaVenta,
        VentaTipo ventaTipo,
        String descripcion,
        VentaEstado ventaEstado,
        double valorFinal
) {
}
