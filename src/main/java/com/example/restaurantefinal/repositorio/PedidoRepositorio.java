package com.example.restaurantefinal.repositorio;

import com.example.restaurantefinal.entidad.Pedido;
import com.example.restaurantefinal.utiles.EstadoPedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Integer> {

    Page<Pedido> findBySedeAndEstado(String sede, EstadoPedido estado, Pageable paginador);

}
