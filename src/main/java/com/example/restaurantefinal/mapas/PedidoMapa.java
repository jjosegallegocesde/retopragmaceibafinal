package com.example.restaurantefinal.mapas;

import com.example.restaurantefinal.dto.PedidoRespuestaDTO;
import com.example.restaurantefinal.dto.PlatoRespuestaDTO;
import com.example.restaurantefinal.entidad.Pedido;
import com.example.restaurantefinal.entidad.Plato;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoMapa {

    @Mappings({
            @Mapping(source = "nombrePlato", target = "nombre"),
            @Mapping(source = "precio", target = "precio"),
            @Mapping(source = "url", target = "foto"),
            @Mapping(source = "descripcion", target = "descripcion")
    })
    PedidoRespuestaDTO transformarPedido(Pedido pedido);
    List<PedidoRespuestaDTO> transformarListaPedidos(List<Pedido> listaPedidos);

}
