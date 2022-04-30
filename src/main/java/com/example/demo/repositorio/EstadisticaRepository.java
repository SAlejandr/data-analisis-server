package com.example.demo.repositorio;

import com.example.demo.modelo.Estadistica;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstadisticaRepository extends MongoRepository<Estadistica, String> {

    public List<Estadistica> findByTituloContains(String titulo);

    public List<Estadistica> findByTituloContainsAndPrivada(String titulo, Boolean privada);

    public Optional<Estadistica> findByNombre(String nombre);
}
