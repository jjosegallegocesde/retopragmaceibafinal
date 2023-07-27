package com.example.restaurantefinal.validaciones;

import com.example.restaurantefinal.entidad.Pedido;


public class PedidoValidacion {

    public  static Boolean validarPresenicaSede(Pedido pedido){
        return  pedido.getSede() == null || pedido.getSede().isEmpty() ||
                pedido.getDetalles() == null || pedido.getDetalles().isEmpty();
    }



}
