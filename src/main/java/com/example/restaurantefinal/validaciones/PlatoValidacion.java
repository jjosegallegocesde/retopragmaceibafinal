package com.example.restaurantefinal.validaciones;

import com.example.restaurantefinal.entidad.Plato;
import org.springframework.stereotype.Component;

@Component
public class PlatoValidacion {


    public boolean validarObligatorios(Plato plato) {
        return  plato.getPrecio() == null || plato.getPrecio() < 0 ||
                plato.getDescripcion() == null || plato.getDescripcion().isEmpty() ||
                plato.getSede() == null || plato.getSede().isEmpty();
    }




}
