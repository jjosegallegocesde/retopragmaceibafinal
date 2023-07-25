package com.example.restaurantefinal.entidad;

import jakarta.persistence.*;

@Entity
@Table(name="platos")
public class Plato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="rol", nullable = false)
    private Character rol;

    @Column(name="nombre", nullable = false)
    private String nombrePlato;

    @Column(name="rol", nullable = false)
    private Integer precio;
    @Column(name="descripcion", nullable = false)
    private String descripcion;

    @Column(name="url", nullable = false)
    private String url;

    @Column(name="categoria", nullable = false)
    private String categoria;

    @Column(name="estado", nullable = false)
    private Boolean estado;

    @Column(name="sede")
    private String sede;

    @Column(name="tiempoPreparacion", nullable = false)
    private Double tiempoPreparacion;

    public Plato() {
    }

    public Plato(Long id, Character rol, String nombrePlato, Integer precio, String descripcion, String url, String categoria, Boolean estado, String sede, Double tiempoPreparacion) {
        this.id = id;
        this.rol = rol;
        this.nombrePlato = nombrePlato;
        this.precio = precio;
        this.descripcion = descripcion;
        this.url = url;
        this.categoria = categoria;
        this.estado = estado;
        this.sede = sede;
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Character getRol() {
        return rol;
    }

    public void setRol(Character rol) {
        this.rol = rol;
    }

    public String getNombrePlato() {
        return nombrePlato;
    }

    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public Double getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(Double tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }
}
