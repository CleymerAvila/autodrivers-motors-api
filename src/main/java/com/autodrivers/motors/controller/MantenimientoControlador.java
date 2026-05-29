package com.autodrivers.motors.controller;

import com.autodrivers.motors.dto.mantenimiento.ActualizarMantenimientoDTO;
import com.autodrivers.motors.dto.mantenimiento.CrearMantenimientoDTO;
import com.autodrivers.motors.dto.mantenimiento.MantenimientoDTO;
import com.autodrivers.motors.service.MantenimientoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/mantenimientos")
public class MantenimientoControlador {

    @Autowired
    public MantenimientoServicio mantenimientoServicio;

    @GetMapping
    public ResponseEntity<List<MantenimientoDTO>> obtenerMantenimientos(){
        return ResponseEntity.ok(this.mantenimientoServicio.obtenerTodosLosMantenimientos());
    }

    @GetMapping("/finalizados")
    public ResponseEntity<List<MantenimientoDTO>> obtenerMantenimientosFinalizados(){
        return ResponseEntity.ok(this.mantenimientoServicio.obtenerMantenimientosFinalizados());
    }

    @GetMapping("/reparacion")
    public ResponseEntity<List<MantenimientoDTO>> obtenerMantenimientosReparacion(){
        return ResponseEntity.ok(this.mantenimientoServicio.obtenerMantenimientosReparacion());
    }

    @GetMapping("/{mantenimientoId}")
    public ResponseEntity<MantenimientoDTO> obtenerMantenimientoPorId(@PathVariable long mantenimientoId){
        var mantenimiento = this.mantenimientoServicio.obtenerMantenimientoPorId(mantenimientoId);

        return ResponseEntity.ok(mantenimiento);
    }

    @PostMapping
    public ResponseEntity<MantenimientoDTO> CrearMantenimiento(@RequestBody CrearMantenimientoDTO datos, UriComponentsBuilder uriComponentsBuilder){
        var mantenimiento = this.mantenimientoServicio.crearMantenimiento(datos);

        URI url = uriComponentsBuilder.path("/mantenimientos/{mantenimientoId}").buildAndExpand(mantenimiento.mantenimientoId()).toUri();

        return ResponseEntity.created(url).body(mantenimiento);
    }

    @DeleteMapping("/{mantenimientoId}")
    public ResponseEntity<Void> eliminarMantenimiento(@PathVariable long mantenimientoId) {
        this.mantenimientoServicio.eliminarMantenimiento(mantenimientoId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{mantenimientoId}")
    public ResponseEntity<MantenimientoDTO> actualizarMantenimiento(@RequestBody ActualizarMantenimientoDTO datos,@PathVariable long mantenimientoId){
        var mantenimiento = this.mantenimientoServicio.actualizarMantenimiento(datos, mantenimientoId);

        return ResponseEntity.ok(mantenimiento);
    }
}
