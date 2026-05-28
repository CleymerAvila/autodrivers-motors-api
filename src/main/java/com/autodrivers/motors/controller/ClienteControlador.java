package com.autodrivers.motors.controller;


import com.autodrivers.motors.dto.cliente.ActualizarClienteDTO;
import com.autodrivers.motors.dto.cliente.ClienteDTO;
import com.autodrivers.motors.dto.cliente.CrearClienteDTO;
import com.autodrivers.motors.service.ClienteServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteControlador {

    @Autowired
    public ClienteServicio clienteServicio;

    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteDTO> obtenerClientePorId(@PathVariable long clienteId){
        var cliente = this.clienteServicio.obtenerClientePorId(clienteId);

        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> crearCliente(@Valid  @RequestBody CrearClienteDTO datos, UriComponentsBuilder uriComponentsBuilder){

        var cliente = this.clienteServicio.crearCliente(datos);

        URI url = uriComponentsBuilder.path("/clientes/{clienteId}").buildAndExpand(cliente.clienteId()).toUri();

        return ResponseEntity.created(url).body(cliente);
    }


    @GetMapping
    public ResponseEntity<List<ClienteDTO>> obtenerClientes(){
        return ResponseEntity.ok(this.clienteServicio.obtenerTodosLosClientes());
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<ClienteDTO> actualizarCliente(@Valid @RequestBody ActualizarClienteDTO datos, @PathVariable long clienteId){
        var cliente = this.clienteServicio.actualizarCliente(clienteId, datos);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable long clienteId){
        this.clienteServicio.eliminarClientePorId(clienteId);
        return ResponseEntity.noContent().build();
    }


}
