package com.example.restaurantefinal.mapas;

import com.example.restaurantefinal.dto.DetallePedidoDTO;
import com.example.restaurantefinal.dto.PedidoRespuestaDTO;
import com.example.restaurantefinal.dto.PlatoRespuestaDTO;
import com.example.restaurantefinal.entidad.DetallePedido;
import com.example.restaurantefinal.entidad.Pedido;
import com.example.restaurantefinal.entidad.Plato;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PedidoMapa {

    //Mapeamos un Pedido para enviar id, sede,estado y
    //detalles(es una lista que tiene que productos y que cantidad de estos esta comprando el cliente)
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "sede", target = "sede"),
            @Mapping(source = "estado", target = "estado"),
            //La entidad Pedido entrega un detalle(que entrega toda la info de un plato y la cantidad)
            //queremos mapear detalle para que solo tenga el nombre y cantidad del plato
            @Mapping(target = "detalles", qualifiedByName = "transformarListaDetallePedido")
    })
    PedidoRespuestaDTO transformarPedido(Pedido pedido);
    List<PedidoRespuestaDTO> transformarListaPedidos(List<Pedido> listaPedidos);

    //metodo encargado de recibir cada plato del pedido y mapearlo para solo
    //entregar el nombre de cada plato y la cantidad del mismo
    @Named("transformarListaDetallePedido")
    default List<DetallePedidoDTO> transformarListaDetallePedido(List<DetallePedido> detalles) {
        return detalles.stream()//se descompone la lista
                .map(this::transformarDetallePedido) //se convierte cada detalle
                .collect(Collectors.toList()); //se vuelve a armar la lista
    }

    @Mapping(target = "nombre", source = "plato.nombrePlato")
    @Mapping(target = "cantidad", source = "cantidad")
    DetallePedidoDTO transformarDetallePedido(DetallePedido detalle);

}
