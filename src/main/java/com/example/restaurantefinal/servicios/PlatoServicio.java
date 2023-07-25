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

    @Autowired
    private PlatoValidacion validacion;

    public PlatoRespuestaDTO registrar(Plato datosPlatoRegistrar) throws Exception{
        try{

            if(!datosPlatoRegistrar.getRol().equals('A')){
                throw new Exception("El ROL no esta autorizado para registrar un plato");
            }
            if(platoRepositorio.existsByNombrePlato(datosPlatoRegistrar.getNombrePlato())){
                throw new Exception("El plato ya existe");
            }
            if(validacion.validarObligatorios(datosPlatoRegistrar)){
                throw new Exception("Hay campos obligatorios que no se enviaron, revisar por favor");
            }

            return platoMapa.transformarPlato(platoRepositorio.save(datosPlatoRegistrar));


        }catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }

    public PlatoRespuestaDTO actualizarInformacion(Plato datosPlatoActualizar, Long idPlato) throws Exception{

        try{

            if(!datosPlatoActualizar.getRol().equals('A')){
                throw new Exception("El ROL no esta autorizado para registrar un plato");
            }

            Optional<Plato> platoOptional = platoRepositorio.findById(idPlato);
            if (!platoOptional.isPresent()) {
                throw new Exception("El plato no existe");
            }

            //El plato existe podemos obtener la info guardada
            Plato platoExistente = platoOptional.get();

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

            //Guardamos los cambios en BD
            Plato platoActualizado = platoRepositorio.save(platoExistente);

            // Devolvemos la respuesta transformada
            return platoMapa.transformarPlato(platoActualizado);

        }catch (Exception error){
            throw new Exception(error.getMessage());
        }

    }


    public PlatoRespuestaDTO actualizarEstado(Plato datosPlatoActualizar, Long idPlato) throws Exception{

        try{

            if(!datosPlatoActualizar.getRol().equals('A')){
                throw new Exception("El ROL no esta autorizado para registrar un plato");
            }

            Optional<Plato> platoOptional = platoRepositorio.findById(idPlato);
            if (!platoOptional.isPresent()) {
                throw new Exception("El plato no existe");
            }

            //El plato existe podemos obtener la info guardada
            Plato platoExistente = platoOptional.get();

            if (datosPlatoActualizar.getEstado() != null) {
                platoExistente.setEstado(datosPlatoActualizar.getEstado());
            }

            //Guardamos los cambios en BD
            Plato platoActualizado = platoRepositorio.save(platoExistente);

            // Devolvemos la respuesta transformada
            return platoMapa.transformarPlato(platoActualizado);

        }catch (Exception error){
            throw new Exception(error.getMessage());
        }

    }

    public Page<PlatoRespuestaDTO> obtenerPlatosPorCategoriaYSede(String categoria, String sede, int numerodeRegistros ) throws Exception{

        try{

            // Crear un objeto Pageable con el tamaño de página y número de página especificados
            Pageable paginador = PageRequest.of(0, numerodeRegistros);

            // Realizar la consulta paginada utilizando el repositorio en BD
            Page<Plato> platosPaginados = platoRepositorio.findByCategoriaAndSede(categoria, sede, paginador);

            // Transformar la lista de Platos a una lista de PlatoRespuestaDTO utilizando el mapper
            Page<PlatoRespuestaDTO> platosRespuesta = platosPaginados.map(plato -> platoMapa.transformarPlato(plato));

            return platosRespuesta;

        }catch (Exception error){
            throw new Exception(error.getMessage());
        }

    }




}
