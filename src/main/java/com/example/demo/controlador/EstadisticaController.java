package com.example.demo.controlador;

import com.example.demo.modelo.Estadistica;
import com.example.demo.servicio.IEstadisticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/stats")
public class EstadisticaController {

    @Autowired
    private IEstadisticaService service;

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
    public ResponseEntity<Estadistica> annadirestadistica(@RequestBody Estadistica estadistica){

        ResponseEntity<Estadistica> respuesta;

        if(!service.buscarPorNombre(estadistica.getNombre()).isPresent()){
            service.guardar(estadistica);
            respuesta = new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            respuesta = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return respuesta;

    }

    @PutMapping("/upd")
    public ResponseEntity<Estadistica> modificarestadistica(@RequestBody Estadistica estadistica){

        ResponseEntity<Estadistica> respuesta;

        if(service.buscarPorNombre(estadistica.getNombre()).isPresent()){
            service.guardar(estadistica);
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
