package com.example.restaurantefinal.servicios;

import com.example.restaurantefinal.dto.PlatoRespuestaDTO;
import com.example.restaurantefinal.entidad.Plato;
import com.example.restaurantefinal.mapas.PlatoMapa;
import com.example.restaurantefinal.repositorio.PlatoRepositorio;
import com.example.restaurantefinal.validaciones.PlatoValidacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlatoServicio {

    @Autowired
    private PlatoRepositorio platoRepositorio;
    @Autowired
    private PlatoMapa platoMapa;


    public PlatoRespuestaDTO registrar(Plato datosPlatoRegistrar) throws Exception{
        try{
            if(!datosPlatoRegistrar.getRol().equals('A')){
                throw new Exception("El ROL no esta autorizado para registrar un plato");
            }
            if(platoRepositorio.existsByNombrePlato(datosPlatoRegistrar.getNombrePlato())){
                throw new Exception("El plato ya existe");
            }
            if(PlatoValidacion.validarObligatorios(datosPlatoRegistrar)){
                throw new Exception("Hay campos obligatorios que no se enviaron, revisar por favor");
            }
            //Si cumplo las validaciones, registro el plato
            return platoMapa.transformarPlato(platoRepositorio.save(datosPlatoRegistrar));
        }catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }

    public PlatoRespuestaDTO actualizarInformacion(Plato datosPlatoActualizar, Long idPlato) throws Exception{
        try{
            //Si el usuario no es ADMININ
            if(datosPlatoActualizar.getRol()!=('A')){
                throw new Exception("El ROL no esta autorizado para registrar un plato");
            }
            //Buscamos el plato a editar
            Optional<Plato> platoOpcional = platoRepositorio.findById(idPlato);
            if (platoOpcional.isEmpty()) { //si el plato no existe en BD
                throw new Exception("El plato no existe");
            }
            //Si el plato existe, podemos obtener la informacion guardada previamente
            Plato platoExistente = platoOpcional.get();
            // Actualizamos los campos permitidos
            if (datosPlatoActualizar.getPrecio() != null) {
                platoExistente.setPrecio(datosPlatoActualizar.getPrecio());
            }
            if (datosPlatoActualizar.getSede() != null) {
                platoExistente.setSede(datosPlatoActualizar.getSede());
            }
            if (datosPlatoActualizar.getDescripcion() != null) {
                platoExistente.setDescripcion(datosPlatoActualizar.getDescripcion());
            }
            //Guardamos los cambios en BD y devolvemos la respuesta transformada
            return platoMapa.transformarPlato( platoRepositorio.save(platoExistente));
        }catch (Exception error){
            throw new Exception(error.getMessage());
        }

    }


    public PlatoRespuestaDTO actualizarEstado(Plato datosPlatoActualizar, Long idPlato) throws Exception{
        try{
            if(datosPlatoActualizar.getRol()!=('A')){
                throw new Exception("El ROL no esta autorizado para registrar un plato");
            }
            Optional<Plato> platoOpcional = platoRepositorio.findById(idPlato);
            if (platoOpcional.isEmpty()) {
                throw new Exception("El plato no existe");
            }
            Plato platoExistente = platoOpcional.get();
            if (datosPlatoActualizar.getEstado() != null) {
                platoExistente.setEstado(datosPlatoActualizar.getEstado());
            }
            return platoMapa.transformarPlato(platoRepositorio.save(platoExistente));
        }catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }

    public Page<PlatoRespuestaDTO> obtenerPlatosPorCategoriaYSede(String categoria, String sede, int numerodeRegistros ) throws Exception{
        try{
            // Crear un objeto Pageable con el tamaño de pagina y numero de registros especificados
            Pageable paginador = PageRequest.of(0, numerodeRegistros);
            // Realizar la consulta paginada utilizando el repositorio en BD
            Page<Plato> platosPaginados = platoRepositorio.findByCategoriaAndSede(categoria, sede, paginador);
            // Transformar la lista de Platos a una lista de PlatoRespuestaDTO utilizando el mapper
            //cada plato de la lista paginada lo transformamos para devolver solo la informacion del DTO
            return platosPaginados.map(plato -> platoMapa.transformarPlato(plato));
        }catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }




}
