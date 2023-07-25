package com.example.restaurantefinal.controlador;

import com.example.restaurantefinal.dto.PlatoDTO;
import com.example.restaurantefinal.dto.PlatoErrorDTO;
import com.example.restaurantefinal.dto.PlatoRespuestaDTO;
import com.example.restaurantefinal.entidad.Plato;
import com.example.restaurantefinal.servicios.PlatoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("restauranteAPI/plato")
public class PlatoControlador {

    @Autowired
    private PlatoServicio servicio;

    @PostMapping
    public ResponseEntity<PlatoDTO> registrar(@RequestBody Plato datosPlatoEnviadoPorCLiente){
        try{

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(servicio.registrar(datosPlatoEnviadoPorCLiente));

        }catch(Exception error){
            PlatoErrorDTO respuestaError= new PlatoErrorDTO();
            respuestaError.setMensajeError(error.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(respuestaError);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<PlatoDTO> actualizar(@PathVariable Long id, @RequestBody Plato datosPlatoEnviadoPorCLiente){

        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(servicio.actualizarInformacion(datosPlatoEnviadoPorCLiente,id));

        }catch(Exception error){
            PlatoErrorDTO respuestaError= new PlatoErrorDTO();
            respuestaError.setMensajeError(error.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(respuestaError);
        }

    }

    @PutMapping("/estado/{id}")
    public ResponseEntity<PlatoDTO> actualizarEstado(@PathVariable Long id, @RequestBody Plato datosPlatoEnviadoPorCLiente){

        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(servicio.actualizarEstado(datosPlatoEnviadoPorCLiente,id));

        }catch(Exception error){
            PlatoErrorDTO respuestaError= new PlatoErrorDTO();
            respuestaError.setMensajeError(error.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(respuestaError);
        }

    }


    @GetMapping
    public ResponseEntity <List<PlatoRespuestaDTO>> obtenerPlatosPaginadosYFiltrados(
            @RequestParam() String categoria,
            @RequestParam() String sede,
            @RequestParam() int numerodeRegistros
    ){
        try{
            // Llamamos al servicio para obtener la respuesta paginada
            Page<PlatoRespuestaDTO> platosPaginados = servicio.obtenerPlatosPorCategoriaYSede(categoria, sede, numerodeRegistros);

            // Creamos una instancia de PlatoRespuestaPaginadaDTO y le pasamos la lista de platos obtenida del Page
            List<PlatoRespuestaDTO> listaPlatos = platosPaginados.getContent();

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(listaPlatos);

        }catch(Exception error){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }

    }


}
