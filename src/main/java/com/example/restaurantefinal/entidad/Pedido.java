package com.example.restaurantefinal.entidad;

import com.example.restaurantefinal.utiles.EstadoPedido;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="rolSolicitud", nullable = false)
    private Character rolSolicitud;

    @Column(name="rolAprobacion", nullable = false)
    private Character rolAprobacion;

    @Column(name="sede")
    private String sede;

    @Enumerated(EnumType.STRING)
    @Column(name="estadoPedido")
    private EstadoPedido estado=EstadoPedido.PENDIENTE;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id")
    private List<DetallePedido> detalles;

    public Pedido() {
    }

    public Pedido(Long id, Character rolSolicitud, Character rolAprobacion, String sede,
                  EstadoPedido estado, List<DetallePedido> detalles) {
        this.id = id;
        this.rolSolicitud = rolSolicitud;
        this.rolAprobacion = rolAprobacion;
        this.sede = sede;
        this.estado = estado;
        this.detalles = detalles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Character getRolSolicitud() {
        return rolSolicitud;
    }

    public void setRolSolicitud(Character rolSolicitud) {
        this.rolSolicitud = rolSolicitud;
    }

    public Character getRolAprobacion() {
        return rolAprobacion;
    }

    public void setRolAprobacion(Character rolAprobacion) {
        this.rolAprobacion = rolAprobacion;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }
}
