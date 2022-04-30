package com.example.demo.servicio;

import com.example.demo.modelo.Formulario;

import java.util.List;
import java.util.Optional;

public interface IFormularioService {

    public List<Formulario> listarFormularios();

    public void guardarFormulario(Formulario formulario);

    public Optional<Formulario> buscarPorId(String id);

    public Optional<Formulario> buscarPorNombre(String nombre);

    public boolean borrarPorID(String id);

}
