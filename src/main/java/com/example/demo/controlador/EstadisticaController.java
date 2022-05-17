package com.example.demo.controlador;

import com.example.demo.DTO.EstadisticaDTO;
import com.example.demo.modelo.Estadistica;
import com.example.demo.modelo.Formulario;
import com.example.demo.servicio.IEstadisticaService;
import com.example.demo.servicio.IFormularioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/stats")
@CrossOrigin("*")
public class EstadisticaController {

    @Autowired
    private IEstadisticaService service;
    @Autowired
    private IFormularioService formularioService;

    @GetMapping("/get")
    public ResponseEntity<Estadistica> buscarPorNombre(@RequestParam String nombre){

        ResponseEntity<Estadistica> respuesta;

        Optional<Estadistica> estadistica = service.buscarPorNombre(nombre);

        if(estadistica.isPresent()){
            respuesta = new ResponseEntity<>(estadistica.get(), HttpStatus.ACCEPTED);
        }else{
            respuesta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return respuesta;
    }

    @GetMapping("/get/{id}")
    public  ResponseEntity<Estadistica> buscarPorId(@PathVariable String id){
        ResponseEntity<Estadistica> respuesta;

        Optional<Estadistica> estadistica = service.buscarPorId(id);

        if(estadistica.isPresent()){
            respuesta = new ResponseEntity<>(estadistica.get(), HttpStatus.ACCEPTED);
        }else{
            respuesta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return respuesta;
    }

    @GetMapping("/get/buscar")
    public List<Estadistica> listarPorTitulo(@RequestParam String titulo){
        return service.listarPorTitulo(titulo);
    }
    @GetMapping("/get/busqueda")
    public List<Estadistica> listarPorTituloYPrivacidad(@RequestParam String titulo, @RequestParam boolean privado){
        return service.listarPorTituloYPrivacidad(titulo,privado);
    }

    @GetMapping("/get/all")
    public List<Estadistica> listarTodos(){

        return service.listarEstadisticas();
    }


    @PostMapping("/add")
    public ResponseEntity<Estadistica> annadirestadistica(@RequestBody EstadisticaDTO estadistica){

        ResponseEntity<Estadistica> respuesta;


        if(!service.buscarPorNombre(estadistica.getNombre()).isPresent()){

            Estadistica stat = Estadistica.builder()
                .nombre(estadistica.getNombre())
                .titulo(estadistica.getTitulo())
                .descripcion(estadistica.getDescripcion())
                .privada(estadistica.isPrivada())
                .fechaLimite(estadistica.getFechaLimite())
                .build();
            service.guardar(stat);
            respuesta = new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            respuesta = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return respuesta;

    }

    @PutMapping("/upd")
    public ResponseEntity<Estadistica> modificarestadistica(@RequestBody EstadisticaDTO estadistica){

        ResponseEntity<Estadistica> respuesta;

        if(service.buscarPorNombre(estadistica.getNombre()).isPresent()){

            Estadistica stat = service.buscarPorNombre(estadistica.getNombre()).get();

            stat.setTitulo(estadistica.getTitulo());
            stat.setDescripcion(estadistica.getDescripcion());
            stat.setPrivada(estadistica.isPrivada());
            stat.setFechaLimite(estadistica.getFechaLimite());

            service.guardar(stat);
            respuesta = new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else{
            respuesta = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return respuesta;

    }
    @PutMapping("/upd/form")
    public ResponseEntity<Estadistica> modificarFomrEstadistica(@RequestParam("formulario") String formulario, @RequestParam() String estadistica){

        ResponseEntity<Estadistica> respuesta;

        if(service.buscarPorNombre(estadistica).isPresent() && formularioService.buscarPorNombre(formulario).isPresent()){

            Estadistica stat = service.buscarPorNombre(estadistica).get();

            Formulario form = formularioService.buscarPorNombre(formulario).get();

            stat.setFormulario(form);

            stat.getCasos().clear();

            service.guardar(stat);
            respuesta = new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else{
            respuesta = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return respuesta;

    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Estadistica> borrarPorId(@PathVariable String id){

        ResponseEntity<Estadistica> respuesta;

        if(service.borrarPorId(id)){
            respuesta = new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else{
            respuesta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return respuesta;
    }

}
