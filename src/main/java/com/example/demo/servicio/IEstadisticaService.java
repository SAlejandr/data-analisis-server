package com.example.demo.servicio;

import com.example.demo.modelo.Estadistica;

import java.util.List;
import java.util.Optional;

public interface IEstadisticaService {

    public List<Estadistica> listarEstadisticas();

    public Optional<Estadistica> buscarPorNombre(String nombre);

    public  Optional<Estadistica> buscarPorId(String id);

    public List<Estadistica> listarPorTitulo(String titulo);

    public List<Estadistica> listarPorTituloYPrivacidad(String titulo, boolean privado);

    public void guardar(Estadistica estadistica);

    public boolean borrarPorId(String id);

}
