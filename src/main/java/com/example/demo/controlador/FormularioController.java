package com.example.demo.controlador;

import com.example.demo.modelo.Formulario;
import com.example.demo.servicio.IFormularioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/forms")
public class FormularioController {

    @Autowired
    private IFormularioService servicio;

    @GetMapping("get")
    public ResponseEntity<Formulario> buscarPorNombre(@PathVariable String nombre){

        ResponseEntity<Formulario> respuesta;

        Optional<Formulario> formulario = servicio.buscarPorNombre(nombre);

        if(formulario.isPresent()){
            respuesta = new ResponseEntity<>(formulario.get(), HttpStatus.ACCEPTED);
        }else{
            respuesta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return respuesta;
    }

    @GetMapping("get/all")
    public List<Formulario> listarTodos(){

        return servicio.listarFormularios();
    }

}
