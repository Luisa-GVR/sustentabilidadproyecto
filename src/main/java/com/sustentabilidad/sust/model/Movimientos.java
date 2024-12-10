package com.sustentabilidad.sust.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos")
public class Movimientos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "movimiento", nullable = false, length = 255)
    private String movimiento;

    // Constructores
    public Movimientos() {
    }

    public Movimientos(LocalDateTime fecha, String movimiento) {
        this.fecha = fecha;
        this.movimiento = movimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }


}
