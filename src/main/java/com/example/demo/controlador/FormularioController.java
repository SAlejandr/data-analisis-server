package com.example.demo.controlador;

import com.example.demo.servicio.IFormularioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FormularioController {

    @Autowired
    private IFormularioService servicio;




}
