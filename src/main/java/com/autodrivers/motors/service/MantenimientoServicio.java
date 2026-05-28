package com.autodrivers.motors.service;

import com.autodrivers.motors.domain.model.Mantenimiento;
import com.autodrivers.motors.domain.model.MantenimientoEstado;
import com.autodrivers.motors.domain.model.MantenimientoTipo;
import com.autodrivers.motors.domain.repository.RepositorioMantenimiento;
import com.autodrivers.motors.dto.mantenimiento.ActualizarMantenimientoDTO;
import com.autodrivers.motors.dto.mantenimiento.CrearMantenimientoDTO;
import com.autodrivers.motors.dto.mantenimiento.MantenimientoDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MantenimientoServicio {

    @Autowired
    public RepositorioMantenimiento repoMantenimiento;


    public MantenimientoDTO crearMantenimiento(CrearMantenimientoDTO datos){
        var mantenimiento = new Mantenimiento(datos);
        return new MantenimientoDTO(this.repoMantenimiento.save(mantenimiento));
    }

    public List<MantenimientoDTO> obtenerTodosLosMantenimientos(){
        return this.repoMantenimiento.findAll().stream().map(MantenimientoDTO::new).toList();
    }

    public List<MantenimientoDTO> obtenerMantenimientosFinalizados(){
        return this.repoMantenimiento.findAll().stream().filter(
                mantenimiento -> mantenimiento.getMantenimientoEstado().equals(MantenimientoEstado.FINALIZADO))
                .map(MantenimientoDTO::new).toList();
    }

    public List<MantenimientoDTO> obtenerMantenimientosReparacion(){
        return this.repoMantenimiento.findAll().stream().filter(
                mantenimiento -> mantenimiento.getMantenimientoTipo().equals(MantenimientoTipo.REPARACION))
                .map(MantenimientoDTO::new).toList();
    }

    public void eliminarMantenimiento(long mantenimientoId) { this.repoMantenimiento.deleteById(mantenimientoId); }

    public MantenimientoDTO obtenerMantenimientoPorId(long mantenimientoId){
        return this.repoMantenimiento.findById(mantenimientoId).map(
                MantenimientoDTO::new)
                .orElseThrow(() ->
                new EntityNotFoundException("Mantenimiento no encontrado")
        );
    }

    public MantenimientoDTO actualizarMantenimiento(ActualizarMantenimientoDTO datos, long mantenimientoId){
        var mantenimiento = this.repoMantenimiento.findById(mantenimientoId)
                .orElseThrow(() -> new EntityNotFoundException("Mantenimiento no encontrado"));

        mantenimiento.actualizar(datos);

        return new MantenimientoDTO(this.repoMantenimiento.save(mantenimiento));
    }
}
