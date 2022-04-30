package com.example.demo.repositorio;

import com.example.demo.modelo.Formulario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FormularioRepository extends MongoRepository<Formulario, String> {

    public Optional<Formulario> findByNombre(String nombre);
}
