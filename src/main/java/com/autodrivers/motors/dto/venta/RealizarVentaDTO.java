package com.autodrivers.motors.dto.venta;

import com.autodrivers.motors.domain.model.Cliente;
import com.autodrivers.motors.domain.model.Vehiculo;
import com.autodrivers.motors.domain.model.VentaEstado;
import com.autodrivers.motors.domain.model.VentaTipo;

import java.time.LocalDateTime;

public record RealizarVentaDTO(
        LocalDateTime fechaVenta,
        VentaTipo ventaTipo,
        long vehiculoId,
        long clienteId,
        String descripcion,
        VentaEstado ventaEstado,
        double valorFinal
) {
}
