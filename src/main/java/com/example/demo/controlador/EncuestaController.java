package com.example.demo.controlador;

import com.example.demo.modelo.Caso;
import com.example.demo.modelo.Estadistica;
import com.example.demo.modelo.Tipo;
import com.example.demo.servicio.IEstadisticaService;
import com.example.demo.servicio.IFormularioService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/encuesta")
@CrossOrigin("*")
public class EncuestaController {
    @Autowired
    private IEstadisticaService estadisticaService;
    @Autowired
    private IFormularioService formularioService;


    @PostMapping("/{estadistica}/save")
    public ResponseEntity<String> guardarCaso(@PathVariable String estadistica, @RequestBody HashMap<String, String> datos){

        ResponseEntity<String> respuesta;

        if(estadisticaService.buscarPorNombre(estadistica).isPresent()){
            Estadistica e = estadisticaService.buscarPorNombre(estadistica).get();

            long numCasos = e.getCasos().isEmpty()? 0 : e.getCasos().size();
            Caso caso = Caso.builder().
                    id(e.getNombre()+"+"+numCasos).
                    formulario(e.getFormulario()).
                    fecha(LocalDateTime.now()).
                    respuesta(datos).
                    build();

            if(e.getFechaLimite() != null && caso.getFecha().isAfter(LocalDateTime.from(e.getFechaLimite())) ){
                Set<Caso> set = e.getCasos();

                set.add(caso);

                e.setCasos(set);

                estadisticaService.guardar(e);

            }

            respuesta = new ResponseEntity<>("Correcto", HttpStatus.ACCEPTED);
        }else{
            respuesta = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return respuesta;

    }

    @GetMapping("/getForm/{nombreStat}")
    public List<Tipo> obtenerFormulario(@PathVariable String nombreStat){

        Optional<Estadistica> estadistica = estadisticaService.buscarPorNombre(nombreStat);

        if(estadistica.isPresent() && estadistica.get().getFormulario() != null){
                return Lists.newArrayList(estadistica.get().getFormulario().getCampos().values());
        }
        else
            return Lists.newArrayList();

    }

}
