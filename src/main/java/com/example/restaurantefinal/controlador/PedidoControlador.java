package com.example.restaurantefinal.controlador;

import com.example.restaurantefinal.dto.PedidoDTO;
import com.example.restaurantefinal.dto.PedidoErrorDTO;
import com.example.restaurantefinal.dto.PedidoRespuestaDTO;
import com.example.restaurantefinal.entidad.Pedido;
import com.example.restaurantefinal.servicios.PedidoServicio;
import com.example.restaurantefinal.utiles.EstadoPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("restauranteAPI/pedido")
public class PedidoControlador {

    @Autowired
    PedidoServicio pedidoServicio;

    @PostMapping
    public ResponseEntity<PedidoDTO> registrar(@RequestBody Pedido pedido){
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(pedidoServicio.crearPedido(pedido));
        }catch(Exception error){
            PedidoErrorDTO respuestaError = new PedidoErrorDTO();
            respuestaError.setMensajeError(error.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(respuestaError);
        }
    }

    @GetMapping
    public ResponseEntity <List<PedidoRespuestaDTO>> obtenerPlatosPaginadosYFiltrados(
            @RequestParam() Character rol,
            @RequestParam() EstadoPedido categoria,
            @RequestParam() String sede,
            @RequestParam() int numerodeRegistros
    ){
        try {
            Page<PedidoRespuestaDTO> edidosPaginados = pedidoServicio.obtenerListaPedidosPorEstadoYSede(rol, sede, categoria, numerodeRegistros);
            List<PedidoRespuestaDTO> listapedidos = edidosPaginados.getContent();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(listapedidos);
        }catch (Exception error){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
    }

    @PutMapping("/estado/{id}")
    public ResponseEntity<PedidoDTO> actualizarEstadoPreparando(@PathVariable Integer id,  @RequestBody Pedido datosPedido){
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(pedidoServicio.actualizarPedidoAEnPreparacion(id,datosPedido));
        }catch(Exception error){
            PedidoErrorDTO respuestaError= new PedidoErrorDTO();
            respuestaError.setMensajeError(error.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(respuestaError);
        }
    }




}
