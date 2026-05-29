package com.autodrivers.motors.service;

import com.autodrivers.motors.domain.model.Venta;
import com.autodrivers.motors.domain.model.VentaEstado;
import com.autodrivers.motors.domain.model.VentaTipo;
import com.autodrivers.motors.domain.repository.RepositorioCliente;
import com.autodrivers.motors.domain.repository.RepositorioVehiculo;
import com.autodrivers.motors.domain.repository.RepositorioVenta;
import com.autodrivers.motors.dto.venta.ActualizarVentaDTO;
import com.autodrivers.motors.dto.venta.RealizarVentaDTO;
import com.autodrivers.motors.dto.venta.VentaDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaServicio {

    @Autowired
    public RepositorioVenta repositorioVenta;

    @Autowired
    public RepositorioCliente repositorioCliente;

    @Autowired
    public RepositorioVehiculo repositorioVehiculo;

    public VentaDTO RealizarVenta(RealizarVentaDTO datos){
        var venta  = new Venta(datos);

        var cliente = this.repositorioCliente.findById(datos.clienteId())
                .orElseThrow(() -> new EntityNotFoundException("El cliente no fue encontrado"));

        var vehiculo = this.repositorioVehiculo.findById(datos.vehiculoId())
                .orElseThrow(() -> new EntityNotFoundException("El vehiculo no fue encontrado"));

        venta.setCliente(cliente);
        venta.setVehiculo(vehiculo);
        return new VentaDTO(this.repositorioVenta.save(venta));
    }

    public List<VentaDTO> obtenerVentas(){
        return this.repositorioVenta.findAll().stream().map(VentaDTO::new).toList();
    }

    public List<VentaDTO> obtenerVentasNuevas(){
        return this.repositorioVenta.findAll().stream().filter(
                venta -> venta.getTipo().equals(VentaTipo.NUEVO))
                .map(VentaDTO::new).toList();
    }

    public List<VentaDTO> obtenerVentasCompletadas(){
        return this.repositorioVenta.findAll().stream().filter(
                venta -> venta.getEstado().equals(VentaEstado.COMPLETADA))
                .map(VentaDTO::new).toList();
    }

    public void eliminarVenta(long ventaId){ this.repositorioVenta.deleteById(ventaId); }

    public VentaDTO obtenerVentaPorId(long ventaId){
        return this.repositorioVenta.findById(ventaId).map(
                VentaDTO::new)
                .orElseThrow(() ->
                new EntityNotFoundException("Venta no encontrada")
        );
    }

    public VentaDTO actualizarVenta(ActualizarVentaDTO datos, long ventaId){
        var venta = this.repositorioVenta.findById(ventaId)
                .orElseThrow(() -> new EntityNotFoundException("Venta no encontrada"));

        venta.actualizar(datos);

        return new VentaDTO(this.repositorioVenta.save(venta));
    }
}
