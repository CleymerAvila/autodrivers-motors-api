package com.autodrivers.motors.controller;

import com.autodrivers.motors.dto.venta.ActualizarVentaDTO;
import com.autodrivers.motors.dto.venta.RealizarVentaDTO;
import com.autodrivers.motors.dto.venta.VentaDTO;
import com.autodrivers.motors.service.VentaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ventas")
public class VentaControlador {

    @Autowired
    public VentaServicio ventaServicio;

    @GetMapping
    public ResponseEntity<List<VentaDTO>> obtenerVentas(){
        return ResponseEntity.ok(this.ventaServicio.obtenerVentas());
    }

    @GetMapping("/nuevas")
    public ResponseEntity<List<VentaDTO>> obtenerVentasNuevas(){
        return ResponseEntity.ok(this.ventaServicio.obtenerVentasNuevas());
    }

    @GetMapping("/completadas")
    public ResponseEntity<List<VentaDTO>> obtenerVentasCompletadas(){
        return ResponseEntity.ok(this.ventaServicio.obtenerVentasCompletadas());
    }

    @GetMapping("/{ventaId}")
    public ResponseEntity<VentaDTO> obtenerVentaPorId(@PathVariable long ventaId){
        var venta = this.ventaServicio.obtenerVentaPorId(ventaId);

        return ResponseEntity.ok(venta);
    }

    @PostMapping
    public ResponseEntity<VentaDTO> realizarVenta(@RequestBody RealizarVentaDTO datos, UriComponentsBuilder uriComponentsBuilder) {
        var venta = this.ventaServicio.RealizarVenta(datos);

        URI url = uriComponentsBuilder.path("/ventas/{ventaId}").buildAndExpand(venta.ventaId()).toUri();

        return ResponseEntity.created(url).body(venta);
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminarVenta(@PathVariable long ventaId){
        this.ventaServicio.eliminarVenta(ventaId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<VentaDTO> actualizarVenta(@RequestBody ActualizarVentaDTO datos, Long ventaId){
        var venta = this.ventaServicio.actualizarVenta(datos, ventaId);

        return ResponseEntity.ok(venta);
    }
}
