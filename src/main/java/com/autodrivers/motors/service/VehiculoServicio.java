package com.autodrivers.motors.service;

import com.autodrivers.motors.domain.model.EstadoVehiculo;
import com.autodrivers.motors.domain.model.Vehiculo;
import com.autodrivers.motors.domain.repository.RepositorioVehiculo;
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


    public VehiculoDTO crearVehiculo(CrearVehiculoDTO datos){
        var vehiculo  = new Vehiculo(datos);
        return new VehiculoDTO(this.repoVehiculo.save(vehiculo));
    }

    public List<VehiculoDTO> obtenerTodosLosVehiculos(){
        return this.repoVehiculo.findAll().stream().map(VehiculoDTO::new).toList();
    }

    public List<VehiculoDTO> obtenerVehiculosDisponibles(){
        return this.repoVehiculo.findAll().stream().filter(
                v -> v.getEstado().equals(EstadoVehiculo.DISPONIBLE))
                .map(VehiculoDTO::new).toList();
    }

    public List<VehiculoDTO> obtenerVehiculosPorMarca(String marca){
        return this.repoVehiculo.findAll().stream().filter(
                vehiculo -> vehiculo.getMarca().equalsIgnoreCase(marca)
                ).map(VehiculoDTO::new).toList();
    }

    public void eliminarVehiculo(long vehiculoId){
        this.repoVehiculo.deleteById(vehiculoId);
    }

    public VehiculoDTO obtenerVehiculoPorId(long vehiculoId){
        return this.repoVehiculo.findById(vehiculoId).map(
                VehiculoDTO::new)
                .orElseThrow(() ->
                new EntityNotFoundException("Vehiculo no encontrado")
        );
    }

    public VehiculoDTO actualizarVehiculo(ActualizarVehiculoDTO datos, long vehiculoId){
        var vehiculo = this.repoVehiculo.findById(vehiculoId)
                .orElseThrow(() -> new EntityNotFoundException("Vehiculo no encontrado"));

        vehiculo.actualizar(datos);

        return new VehiculoDTO(this.repoVehiculo.save(vehiculo));
    }
}
