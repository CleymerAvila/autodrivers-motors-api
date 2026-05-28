package com.autodrivers.motors.domain.model;

import com.autodrivers.motors.dto.venta.ActualizarVentaDTO;
import com.autodrivers.motors.dto.venta.RealizarVentaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "venta")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "ventaId")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ventaId;
    private LocalDateTime fechaVenta;
    private VentaTipo ventaTipo;
    @OneToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;
    @OneToMany
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    private String descripcion;
    private VentaEstado ventaEstado;
    private double valorFinal;

    public Venta(RealizarVentaDTO datos) {
        this.fechaVenta = datos.fechaVenta();
        this.ventaTipo = datos.ventaTipo();
        this.vehiculo = datos.vehiculo();
        this.cliente = datos.cliente();
        this.descripcion = datos.descripcion();
        this.ventaEstado = VentaEstado.PENDIENTE;
        this.valorFinal = datos.valorFinal();
    }

    public void actualizar(ActualizarVentaDTO datos) {
        if(!(datos.fechaVenta() != null) && !datos.fechaVenta().isEqual(this.fechaVenta)) {
            this.fechaVenta = datos.fechaVenta();
        }

        if(!(datos.ventaTipo() != null) && !datos.ventaTipo().equals(this.ventaTipo)) {
            this.ventaTipo = datos.ventaTipo();
        }

        if(!(datos.vehiculo() != null) && !datos.vehiculo().equals(this.vehiculo)) {
            this.vehiculo = datos.vehiculo();
        }

        if(!(datos.cliente() != null) && !datos.cliente().equals(this.cliente)) {
            this.cliente = datos.cliente();
        }

        if(!(datos.descripcion() != null) && !datos.descripcion().equals(this.descripcion)) {
            this.descripcion = datos.descripcion();
        }

        if(!(datos.ventaEstado() != null) && !datos.ventaEstado().equals(this.ventaEstado)) {
            this.ventaEstado = datos.ventaEstado();
        }

        if(datos.valorFinal() !=0 && datos.valorFinal() != this.valorFinal) {
            this.valorFinal = datos.valorFinal();
        }
    }
}
