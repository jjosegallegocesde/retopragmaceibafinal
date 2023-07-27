package com.example.restaurantefinal.dto;

public class DetallePedidoDTO {
    private String nombre;
    private Integer cantidad;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getcantidad() {
        return cantidad;
    }

    public void setcantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
