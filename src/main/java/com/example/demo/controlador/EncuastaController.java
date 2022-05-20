package com.example.demo.controlador;

import com.example.demo.modelo.Estadistica;
import com.example.demo.modelo.Formulario;
import com.example.demo.servicio.IEstadisticaService;
import com.example.demo.servicio.IFormularioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/encuesta")
@CrossOrigin("*")
public class EncuastaController {
    @Autowired
    private IEstadisticaService estadisticaService;
    @Autowired
    private IFormularioService formularioService;


    @PostMapping("/save")
    public ResponseEntity<String> guardarCaso(@RequestParam String estadistica, @RequestBody HashMap<String, String> datos){

        ResponseEntity<String> respuesta;

        if(estadisticaService.buscarPorNombre(estadistica).isPresent()){
            Estadistica e = estadisticaService.buscarPorNombre(estadistica).get();

            respuesta = new ResponseEntity<>("Correcto", HttpStatus.ACCEPTED);
        }else{
            respuesta = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return respuesta;

    }
//
//
}
