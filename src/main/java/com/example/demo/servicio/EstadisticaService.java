package com.example.demo.servicio;

import com.example.demo.modelo.Estadistica;
import com.example.demo.repositorio.EstadisticaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class EstadisticaService implements IEstadisticaService{

    @Autowired
    private EstadisticaRepository repository;

    @Override
    public List<Estadistica> listarEstadisticas() {
        return repository.findAll();
    }

    @Override
    public Optional<Estadistica> buscarPorNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    @Override
    public Optional<Estadistica> buscarPorId(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Estadistica> listarPorTitulo(String titulo) {
        return repository.findByTituloContains(titulo);
    }

    @Override
    public List<Estadistica> listarPorTituloYPrivacidad(String titulo, boolean privado) {
        return repository.findByTituloContainsAndPrivada(titulo, privado);
    }

    @Override
    public void guardar(Estadistica estadistica) {

        repository.save(estadistica);
    }

    @Override
    public boolean borrarPorId(String id) {

        boolean exito = repository.existsById(id);

        if(exito)
            repository.deleteById(id);

        return exito;
    }
}
