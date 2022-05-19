package com.example.demo.controlador;

import com.example.demo.modelo.Formulario;
import com.example.demo.modelo.Tipo;
import com.example.demo.servicio.IFormularioService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/forms")
@CrossOrigin("*")
public class FormularioController {

    @Autowired
    private IFormularioService servicio;

    @GetMapping("/get")
    public ResponseEntity<Formulario> buscarPorNombre(@RequestParam String nombre){

        ResponseEntity<Formulario> respuesta;

        Optional<Formulario> formulario = servicio.buscarPorNombre(nombre);

        if(formulario.isPresent()){
            respuesta = new ResponseEntity<>(formulario.get(), HttpStatus.ACCEPTED);
        }else{
            respuesta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return respuesta;
    }



    @GetMapping("/get/all")
    public List<Formulario> listarTodos(){

        return servicio.listarFormularios();
    }

    @GetMapping("/get/all/priv")
    public List<Formulario> listarPrivados(){

        return servicio.listarFormulariosPrivados();
    }
    @GetMapping("/get/all/publi")
    public List<Formulario> listarPublicos(){

        return servicio.listarFormulariosPublicos();
    }

    @GetMapping("/get/campos/{form}")
    public List<Tipo> listarCampos(@PathVariable String form){

        return Lists.newArrayList(servicio.buscarPorNombre(form).get().getCampos().values());
    }

    @PostMapping("/add")
    public ResponseEntity<Formulario> annadirFormulario(@RequestBody Formulario formulario){

        ResponseEntity<Formulario> respuesta;

        if(!servicio.buscarPorNombre(formulario.getNombre()).isPresent()){
            servicio.guardarFormulario(formulario);
            respuesta = new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            respuesta = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return respuesta;

    }

    @PutMapping("/upd")
    public ResponseEntity<Formulario> modificarFormulario(@RequestBody Formulario formulario){

        ResponseEntity<Formulario> respuesta;

        if(servicio.buscarPorNombre(formulario.getNombre()).isPresent()){
            servicio.guardarFormulario(formulario);
            respuesta = new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else{
            respuesta = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return respuesta;

    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Formulario> borrarPorId(@PathVariable String id){

        ResponseEntity<Formulario> respuesta;

        if(servicio.borrarPorID(id)){
            respuesta = new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else{
            respuesta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return respuesta;
    }



}
