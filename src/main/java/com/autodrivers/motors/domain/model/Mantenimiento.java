package com.autodrivers.motors.domain.model;

import com.autodrivers.motors.dto.mantenimiento.ActualizarMantenimientoDTO;
import com.autodrivers.motors.dto.mantenimiento.CrearMantenimientoDTO;
import com.autodrivers.motors.dto.mantenimiento.MantenimientoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "mantenimiento")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "mantenimientoId")
public class Mantenimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mantenimientoId;
    @OneToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;
    private MantenimientoTipo mantenimientoTipo;
    private String descripcion;
    private LocalDateTime fechaServicio;
    private MantenimientoEstado mantenimientoEstado;
    private double costoRepuesto;
    private double costoManoObra;
    private double costoTotal;

    public Mantenimiento(CrearMantenimientoDTO datos){
        this.vehiculo = datos.vehiculo();
        this.mantenimientoTipo = datos.mantenimientoTipo();
        this.descripcion = datos.descripcion();
        this.fechaServicio = datos.fechaServicio();
        this.mantenimientoEstado = MantenimientoEstado.EN_CURSO;
        this.costoRepuesto = datos.costoRepuesto();
        this.costoManoObra = datos.costoManoObra();
        this.costoTotal = datos.costoTotal();
    }


    public void actualizar(ActualizarMantenimientoDTO datos){
        if(!(datos.mantenimientoTipo() != null) && !datos.mantenimientoTipo().equals(this.mantenimientoTipo)) {
            this.mantenimientoTipo = datos.mantenimientoTipo();
        }

        if(!(datos.descripcion() != null) && !datos.descripcion().equals(this.descripcion)) {
            this.descripcion = datos.descripcion();
        }

        if(!(datos.fechaServicio() != null) && !datos.fechaServicio().isEqual(this.fechaServicio)) {
            this.fechaServicio = datos.fechaServicio();
        }

        if(!(datos.mantenimientoEstado() != null) && !datos.mantenimientoEstado().equals(this.mantenimientoEstado)) {
            this.mantenimientoEstado = datos.mantenimientoEstado();
        }

        if(datos.costoRepuesto() !=0 && datos.costoRepuesto() != this.costoRepuesto) {
            this.costoRepuesto = datos.costoRepuesto();
        }

        if(datos.costoManoObra() !=0 && datos.costoManoObra() != this.costoManoObra) {
            this.costoManoObra = datos.costoManoObra();
        }

        if(datos.costoTotal() !=0 && datos.costoTotal() != this.costoTotal) {
            this.costoTotal = datos.costoTotal();
        }
    }

}
