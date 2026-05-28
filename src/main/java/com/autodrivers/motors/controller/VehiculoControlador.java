package com.autodrivers.motors.controller;


import com.autodrivers.motors.dto.cliente.ActualizarClienteDTO;
import com.autodrivers.motors.dto.vehiculo.ActualizarVehiculoDTO;
import com.autodrivers.motors.dto.vehiculo.CrearVehiculoDTO;
import com.autodrivers.motors.dto.vehiculo.VehiculoDTO;
import com.autodrivers.motors.service.VehiculoServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vehiculos")
public class VehiculoControlador {

    @Autowired
    public VehiculoServicio vehiculoServicio;


    @GetMapping
    public ResponseEntity<List<VehiculoDTO>> obtenerVehiculos(){
        return ResponseEntity.ok(this.vehiculoServicio.obtenerTodosLosVehiculos());
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<VehiculoDTO>> obtenerVehiculosDisponibles(){
        return ResponseEntity.ok(this.vehiculoServicio.obtenerVehiculosDisponibles());
    }

    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<VehiculoDTO>> obtenerVehiculosPorMarca(@PathVariable String marca){
        return ResponseEntity.ok(this.vehiculoServicio.obtenerVehiculosPorMarca(marca));
    }

    @GetMapping("/{vehiculoId}")
    public ResponseEntity<VehiculoDTO> obtenerVehiculoPorId(@PathVariable long vehiculoId){
        var vehiculo = this.vehiculoServicio.obtenerVehiculoPorId(vehiculoId);

        return ResponseEntity.ok(vehiculo);
    }

    @PostMapping
    public ResponseEntity<VehiculoDTO> crearVehiculo(@Valid  @RequestBody CrearVehiculoDTO datos, UriComponentsBuilder uriComponentsBuilder){

        var vehiculo = this.vehiculoServicio.crearVehiculo(datos);

        URI url = uriComponentsBuilder.path("/vehiculos/{vehiculoId}").buildAndExpand(vehiculo.vehiculoId()).toUri();

        return ResponseEntity.created(url).body(vehiculo);
    }

    @DeleteMapping("/{vehiculoId}")
    public ResponseEntity<Void> eliminarVehiculo(@PathVariable long vehiculoId){
        this.vehiculoServicio.eliminarVehiculo(vehiculoId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{vehiculoId}")
    public ResponseEntity<VehiculoDTO> actualizarVehiculo(@Valid @RequestBody ActualizarVehiculoDTO datos, @PathVariable long vehiculoId){
        var vehiculo = this.vehiculoServicio.actualizarVehiculo(datos, vehiculoId);

        return ResponseEntity.ok(vehiculo);
    }


}
