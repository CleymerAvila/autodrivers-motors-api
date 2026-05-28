package com.autodrivers.motors.service;

import com.autodrivers.motors.domain.model.Cliente;
import com.autodrivers.motors.domain.repository.RepositorioCliente;
import com.autodrivers.motors.dto.cliente.ActualizarClienteDTO;
import com.autodrivers.motors.dto.cliente.ClienteDTO;
import com.autodrivers.motors.dto.cliente.CrearClienteDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServicio {

    @Autowired
    public RepositorioCliente repoCliente;


    public ClienteDTO crearCliente(CrearClienteDTO datos){
        var cliente = new Cliente(datos);

        return new ClienteDTO(this.repoCliente.save(cliente));
    }

    public ClienteDTO obtenerClientePorId(long clienteId){
        return this.repoCliente.findById(clienteId)
                .map(ClienteDTO::new)
                .orElseThrow(()-> new EntityNotFoundException("Cliente no existe"));
    }

    public List<ClienteDTO> obtenerTodosLosClientes(){
        return this.repoCliente.findAll().stream().map(ClienteDTO::new).toList();
    }

    public void eliminarClientePorId(long clienteId){
        this.repoCliente.deleteById(clienteId);
    }

    public ClienteDTO actualizarCliente(long clienteId, ActualizarClienteDTO datos){
        var cliente = this.repoCliente.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));


        cliente.actualizar(datos);

        return new ClienteDTO(this.repoCliente.save(cliente));
    }
}
