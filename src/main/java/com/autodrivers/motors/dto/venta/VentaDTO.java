package com.autodrivers.motors.dto.venta;

import com.autodrivers.motors.domain.model.*;

import java.time.LocalDateTime;

public record VentaDTO(
        Long ventaId,
        LocalDateTime fechaVenta,
        VentaTipo ventaTipo,
        Vehiculo vehiculo,
        Cliente cliente,
        String descripcion,
        VentaEstado ventaEstado,
        double valorFinal
) {

    public VentaDTO (Venta venta) {
        this(venta.getVentaId(), venta.getFechaVenta(), venta.getVentaTipo(),
                venta.getVehiculo(), venta.getCliente(), venta.getDescripcion(),
                venta.getVentaEstado(), venta.getValorFinal()
        );
    }
}
