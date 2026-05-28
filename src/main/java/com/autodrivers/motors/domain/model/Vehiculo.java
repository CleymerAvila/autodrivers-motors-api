package com.autodrivers.motors.domain.model;

import com.autodrivers.motors.dto.vehiculo.ActualizarVehiculoDTO;
import com.autodrivers.motors.dto.vehiculo.CrearVehiculoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "vehiculos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "vehiculoId")
public class Vehiculo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehiculoId;
    private String placa;
    private String marca;
    private String modelo;
    private LocalDate anioFabricacion;
    private double precio;
    @Enumerated(EnumType.STRING)
    private EstadoVehiculo estado;
    private String color;

    public Vehiculo(CrearVehiculoDTO datos){
        this.placa = datos.placa();
        this.color = datos.color();
        this.marca = datos.marca();
        this.modelo = datos.modelo();
        this.precio = datos.precio();
        this.estado = EstadoVehiculo.DISPONIBLE;
        this.anioFabricacion = datos.anioFabricacion();
    }

    public void actualizar(ActualizarVehiculoDTO datos){
        if(!datos.marca().isBlank() && !datos.marca().equals(this.marca)){
            this.marca = datos.marca();
        }

        if(!datos.modelo().isBlank() && !datos.modelo().equals(this.modelo)){
            this.modelo = datos.modelo();
        }

        if(!datos.color().isBlank() && !datos.modelo().equals(this.color)){
            this.color = datos.color();
        }

        if(datos.precio()!=0 && datos.precio()!=this.precio){
            this.precio = datos.precio();
        }

        if(this.estado.equals(EstadoVehiculo.VENDIDO)){
            throw new IllegalArgumentException("No es posible actualizar este vehiculo ya que no se encuentra disponible");
        }

        if(datos.estado().equals(EstadoVehiculo.VENDIDO)){
            throw new IllegalArgumentException("No es posible actualizar el estado del vehiculo a vendido");

        }
        this.estado = datos.estado();
    }
}
