package com.example.restaurantefinal.servicios;

import com.example.restaurantefinal.dto.PedidoRespuestaDTO;
import com.example.restaurantefinal.entidad.DetallePedido;
import com.example.restaurantefinal.entidad.Pedido;
import com.example.restaurantefinal.entidad.Plato;
import com.example.restaurantefinal.mapas.PedidoMapa;
import com.example.restaurantefinal.repositorio.PedidoRepositorio;
import com.example.restaurantefinal.repositorio.PlatoRepositorio;
import com.example.restaurantefinal.utiles.EstadoPedido;
import com.example.restaurantefinal.validaciones.PedidoValidacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServicio {

    @Autowired
    PedidoMapa pedidoMapa;
    @Autowired
    PedidoRepositorio pedidoRepositorio;
    @Autowired
    PlatoRepositorio platoRepositorio;

    public PedidoRespuestaDTO crearPedido(Pedido datosDelPedido) throws Exception{
        try{
            //solo el cliente C puede crear el pedido
            if(datosDelPedido.getRolSolicitud()!=('C')){
                throw new Exception("El ROL no esta autorizado para crear un pedido");
            }
            //revisamos campos obligatorios
            if(PedidoValidacion.validarPresenicaSede(datosDelPedido)){
                throw new Exception("Hay campos obligatorios que no se enviaron, revisar por favor");
            }
            //recorro el detalle del pedido (todos los productos que eligio el cliente)
            for (DetallePedido detalle : datosDelPedido.getDetalles()) {
                Long idPlato = detalle.getPlato().getId(); //cada plato en el detalle tiene un id
                Optional<Plato> platoOptional = platoRepositorio.findById(idPlato); //buscamos el plato en cuestion
                detalle.getPlato().setNombrePlato(platoOptional.get().getNombrePlato()); //llevamos el nombre del plato a lo que vamos a guardar
            }
            return pedidoMapa.transformarPedido(pedidoRepositorio.save(datosDelPedido)); //guardamos el pedido
        }catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }

    public Page<PedidoRespuestaDTO> obtenerListaPedidosPorEstadoYSede(Character rol, String sede, EstadoPedido estado, int numerodeRegistros) throws Exception{
        try{

            //solo el Admin A puede listar los pedidos
            if(rol!=('A')){
                throw new Exception("El ROL no esta autorizado para crear un pedido");
            }
            Pageable paginador = PageRequest.of(0, numerodeRegistros);
            Page<Pedido> pedidosPaginados=pedidoRepositorio.findBySedeAndEstado(sede,estado,paginador);
            return pedidosPaginados.map(pedido -> pedidoMapa.transformarPedido(pedido));
        }catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }

    public PedidoRespuestaDTO actualizarPedidoAEnPreparacion(Integer idPedido, Pedido datosPedido) throws Exception{
        try{
            //solo el Admin A puede cambiar el estado
            if(datosPedido.getRolAprobacion()!=('A')){
                throw new Exception("El ROL no esta autorizado para crear un pedido");
            }
            Optional<Pedido> pedidoOpcional = pedidoRepositorio.findById(idPedido);
            if (pedidoOpcional.isEmpty()) {
                throw new Exception("El pedido no existe");
            }
            Pedido pedidoExistente = pedidoOpcional.get();
            pedidoExistente.setEstado(EstadoPedido.EN_PREPARACION);
            return pedidoMapa.transformarPedido(pedidoRepositorio.save(pedidoExistente));
        }catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }


}
