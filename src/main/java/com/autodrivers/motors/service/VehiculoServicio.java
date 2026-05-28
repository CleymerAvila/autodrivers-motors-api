package com.autodrivers.motors.service;

import com.autodrivers.motors.domain.model.EstadoVehiculo;
import com.autodrivers.motors.domain.model.Vehiculo;
import com.autodrivers.motors.domain.repository.RepositorioVehiculo;
import com.autodrivers.motors.dto.conversor.TasaCambio;
import com.autodrivers.motors.dto.vehiculo.ActualizarVehiculoDTO;
import com.autodrivers.motors.dto.vehiculo.CrearVehiculoDTO;
import com.autodrivers.motors.dto.vehiculo.VehiculoDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoServicio {

    @Autowired
    public RepositorioVehiculo repoVehiculo;
    @Autowired
    public ConvertidorMonedaServicio convertidorMonedaServicio;


    public VehiculoDTO crearVehiculo(CrearVehiculoDTO datos){
        var vehiculo  = new Vehiculo(datos);

        var vehiculoGuardado = this.repoVehiculo.save(vehiculo);
        TasaCambio tasaCambioUSD = consultarCambio("USD", vehiculoGuardado.getPrecio());
        TasaCambio tasaCambioEUR = consultarCambio("EUR", vehiculoGuardado.getPrecio());

        System.out.println("Tasa cambio usd: " + tasaCambioUSD);
        System.out.println("Tasa cambio eur: " + tasaCambioEUR);
        return  new VehiculoDTO(vehiculoGuardado, tasaCambioUSD, tasaCambioEUR);
    }

    public List<VehiculoDTO> obtenerTodosLosVehiculos(){
        return this.repoVehiculo.findAll().stream().map( vehiculo -> {
            TasaCambio tasaCambioUsd = consultarCambio("USD", vehiculo.getPrecio());
            TasaCambio tasaCambioEur= consultarCambio("EUR", vehiculo.getPrecio());

            return new VehiculoDTO(vehiculo, tasaCambioUsd, tasaCambioEur);
        }).toList();
    }

    public List<VehiculoDTO> obtenerVehiculosDisponibles(){
        return this.repoVehiculo.findAll().stream().filter(
                v -> v.getEstado().equals(EstadoVehiculo.DISPONIBLE))
                .map( vehiculo -> {
                    TasaCambio tasaCambioUsd = consultarCambio("USD", vehiculo.getPrecio());
                    TasaCambio tasaCambioEur = consultarCambio("EUR", vehiculo.getPrecio());

                    return new VehiculoDTO(vehiculo, tasaCambioUsd, tasaCambioEur);
                }).toList();
    }

    public List<VehiculoDTO> obtenerVehiculosPorMarca(String marca){
        return this.repoVehiculo.findAll().stream().filter(
                vehiculo -> vehiculo.getMarca().equalsIgnoreCase(marca)
                ).map(
                vehiculo -> {
                    TasaCambio tasaCambioUsd = consultarCambio("USD", vehiculo.getPrecio());
                    TasaCambio tasaCambioEur = consultarCambio("EUR", vehiculo.getPrecio());

                    return new VehiculoDTO(vehiculo, tasaCambioUsd, tasaCambioEur);
                }).toList();
    }

    public void eliminarVehiculo(long vehiculoId){
        this.repoVehiculo.deleteById(vehiculoId);
    }

    public VehiculoDTO obtenerVehiculoPorId(long vehiculoId){
        return this.repoVehiculo.findById(vehiculoId).map( vehiculo -> {
                    TasaCambio tasaCambioUsd = consultarCambio("USD", vehiculo.getPrecio());
                    TasaCambio tasaCambioEur = consultarCambio("EUR", vehiculo.getPrecio());

                    return new VehiculoDTO(vehiculo, tasaCambioUsd, tasaCambioEur);
                })
                .orElseThrow(() ->
                new EntityNotFoundException("Vehiculo no encontrado")
        );
    }

    private TasaCambio consultarCambio(String paisDestino, double valor){
        return this.convertidorMonedaServicio.consultarMonedaCambio(
                "COP",
                paisDestino,
                valor
        );
    }

    public VehiculoDTO actualizarVehiculo(ActualizarVehiculoDTO datos, long vehiculoId){
        var vehiculo = this.repoVehiculo.findById(vehiculoId)
                .orElseThrow(() -> new EntityNotFoundException("Vehiculo no encontrado"));

        vehiculo.actualizar(datos);

        var vehiculoGuardado = this.repoVehiculo.save(vehiculo);
        TasaCambio tasaCambioUSD = consultarCambio("USD", vehiculoGuardado.getPrecio());
        TasaCambio tasaCambioEUR = consultarCambio("EUR", vehiculoGuardado.getPrecio());
        return new VehiculoDTO(vehiculoGuardado, tasaCambioUSD, tasaCambioEUR);
    }
}
