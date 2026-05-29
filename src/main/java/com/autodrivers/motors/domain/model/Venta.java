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
@Table(name = "ventas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "ventaId")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ventaId;
    private LocalDateTime fechaHora;
    @Enumerated(EnumType.STRING)
    private VentaTipo tipo;
    @OneToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;
    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    private String descripcion;
    @Enumerated(EnumType.STRING)
    private VentaEstado estado;
    private double valorFinal;

    public Venta(RealizarVentaDTO datos) {
        this.fechaHora = datos.fechaVenta();
        this.tipo = datos.ventaTipo();
        this.descripcion = datos.descripcion();
        this.estado = VentaEstado.PENDIENTE;
        this.valorFinal = datos.valorFinal();
    }

    public void actualizar(ActualizarVentaDTO datos) {
        if(!(datos.fechaVenta() != null) && !datos.fechaVenta().isEqual(this.fechaHora)) {
            this.fechaHora = datos.fechaVenta();
        }

        if(!(datos.ventaTipo() != null) && !datos.ventaTipo().equals(this.tipo)) {
            this.tipo = datos.ventaTipo();
        }


        if(!(datos.descripcion() != null) && !datos.descripcion().equals(this.descripcion)) {
            this.descripcion = datos.descripcion();
        }

        if(!(datos.ventaEstado() != null) && !datos.ventaEstado().equals(this.estado)) {
            this.estado = datos.ventaEstado();
        }

        if(datos.valorFinal() !=0 && datos.valorFinal() != this.valorFinal) {
            this.valorFinal = datos.valorFinal();
        }
    }
}
