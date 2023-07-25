package com.example.restaurantefinal.repositorio;

import com.example.restaurantefinal.entidad.Plato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatoRepositorio extends JpaRepository<Plato,Long> {
}
