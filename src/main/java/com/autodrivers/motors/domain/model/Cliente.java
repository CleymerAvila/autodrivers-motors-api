package com.autodrivers.motors.domain.model;

import com.autodrivers.motors.dto.cliente.ActualizarClienteDTO;
import com.autodrivers.motors.dto.cliente.CrearClienteDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "clienteId")
public class Cliente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteId;
    private String nombreCompleto;
    private String dni;
    private String telefono;
    private String direccion;
    private String correoElectronico;

    public Cliente(CrearClienteDTO datos){
        this.dni = datos.dni();
        this.nombreCompleto = datos.nombreCompleto();
        this.telefono = datos.telefono();
        this.direccion = datos.direccion();
        this.correoElectronico = datos.correoElectronico();
    }

    public void actualizar(ActualizarClienteDTO datos){
        if(!datos.nombreCompleto().equals(this.nombreCompleto) && !datos.nombreCompleto().isBlank()){
            this.nombreCompleto = datos.nombreCompleto();
        }

        if(!datos.telefono().equals(this.telefono) && !datos.telefono().isBlank()){
            this.telefono = datos.telefono();
        }

        if(!datos.direccion().equals(this.direccion) && !datos.direccion().isBlank()){
            this.direccion = datos.direccion();
        }

        if(!datos.correoElectronico().equals(this.correoElectronico) && !datos.correoElectronico().isBlank()){
            this.correoElectronico = datos.correoElectronico();
        }
    }
}
