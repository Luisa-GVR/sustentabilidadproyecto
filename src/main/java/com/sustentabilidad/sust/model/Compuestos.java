package com.sustentabilidad.sust.model;

import jakarta.persistence.*;

@Entity
@Table(name = "compuestos")


public class Compuestos {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombreCompuesto", nullable = false, length = 255)
    private String nombreCompuesto;

    @Column(name = "organico", nullable = false)
    private boolean organico;

    @Column(name = "peso", nullable = false)
    private double peso;

    @Column(name = "explosivo", nullable = false)
    private boolean explosivo;

    @Column(name = "inflamable", nullable = false)
    private boolean inflamable;

    @Column(name = "carburante", nullable = false)
    private boolean carburante;

    @Column(name = "presion", nullable = false)
    private boolean presion;

    @Column(name = "corrosion", nullable = false)
    private boolean corrosion;

    @Column(name = "toxicidad", nullable = false)
    private boolean toxicidad;

    @Column(name = "quimicoNocivo", nullable = false)
    private boolean quimicoNocivo;

    @Column(name = "medioAmbiente", nullable = false)
    private boolean medioAmbiente;

    @Column(name = "peligroSalud", nullable = false)
    private boolean peligroSalud;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCompuesto() {
        return nombreCompuesto;
    }

    public void setNombreCompuesto(String nombreCompuesto) {
        this.nombreCompuesto = nombreCompuesto;
    }

    public boolean isOrganico() {
        return organico;
    }

    public void setOrganico(boolean organico) {
        this.organico = organico;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public boolean isExplosivo() {
        return explosivo;
    }

    public void setExplosivo(boolean explosivo) {
        this.explosivo = explosivo;
    }

    public boolean isInflamable() {
        return inflamable;
    }

    public void setInflamable(boolean inflamable) {
        this.inflamable = inflamable;
    }

    public boolean isCarburante() {
        return carburante;
    }

    public void setCarburante(boolean carburante) {
        this.carburante = carburante;
    }

    public boolean isPresion() {
        return presion;
    }

    public void setPresion(boolean presion) {
        this.presion = presion;
    }

    public boolean isCorrosion() {
        return corrosion;
    }

    public void setCorrosion(boolean corrosion) {
        this.corrosion = corrosion;
    }

    public boolean isToxicidad() {
        return toxicidad;
    }

    public void setToxicidad(boolean toxicidad) {
        this.toxicidad = toxicidad;
    }

    public boolean isQuimicoNocivo() {
        return quimicoNocivo;
    }

    public void setQuimicoNocivo(boolean quimicoNocivo) {
        this.quimicoNocivo = quimicoNocivo;
    }

    public boolean isMedioAmbiente() {
        return medioAmbiente;
    }

    public void setMedioAmbiente(boolean medioAmbiente) {
        this.medioAmbiente = medioAmbiente;
    }

    public boolean isPeligroSalud() {
        return peligroSalud;
    }

    public void setPeligroSalud(boolean peligroSalud) {
        this.peligroSalud = peligroSalud;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Compuestos() {
    }
    public Compuestos(String nombreCompuesto, boolean organico, int peso, boolean explosivo, boolean inflamable, boolean carburante, boolean presion, boolean corrosion, boolean toxicidad, boolean quimicoNocivo, boolean medioAmbiente, boolean peligroSalud, String descripcion) {
        this.nombreCompuesto = nombreCompuesto;
        this.organico = organico;
        this.peso = peso;
        this.explosivo = explosivo;
        this.inflamable = inflamable;
        this.carburante = carburante;
        this.presion = presion;
        this.corrosion = corrosion;
        this.toxicidad = toxicidad;
        this.quimicoNocivo = quimicoNocivo;
        this.medioAmbiente = medioAmbiente;
        this.peligroSalud = peligroSalud;
        this.descripcion = descripcion;
    }
}
