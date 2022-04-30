package com.example.demo.servicio;

import com.example.demo.modelo.Formulario;
import com.example.demo.repositorio.FormularioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormularioService implements  IFormularioService{

    @Autowired
    private FormularioRepository repository;

    @Override
    public List<Formulario> listarFormularios() {
        return repository.findAll();
    }

    @Override
    public void guardarFormulario(Formulario formulario) {

        repository.save(formulario);

    }

    @Override
    public Optional<Formulario> buscarPorId(String id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Formulario> buscarPorNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    @Override
    public boolean borrarPorID(String id) {

        boolean exito = repository.existsById(id);

        if(exito)
            repository.deleteById(id);

        return exito;
    }
}
