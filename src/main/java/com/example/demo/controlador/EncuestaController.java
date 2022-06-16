package com.example.demo.controlador;

import com.example.demo.modelo.*;
import com.example.demo.servicio.IEstadisticaService;
import com.example.demo.servicio.IFormularioService;
import com.example.demo.servicio.IResultadosService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping(value = "/encuesta")
@CrossOrigin("*")
public class EncuestaController {
    @Autowired
    private IEstadisticaService estadisticaService;
    @Autowired
    private IFormularioService formularioService;
    @Autowired
    private IResultadosService resultadosService;


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

            if(e.getFechaLimite() != null ){
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

    @GetMapping("/getResult/{nombreStat}")
    public ResponseEntity<Resultado> darResultado(@PathVariable String nombreStat){

        Optional<Estadistica> estadistica = estadisticaService.buscarPorNombre(nombreStat);

        ResponseEntity<Resultado> respueta;

        if(estadistica.isPresent()){
            Resultado resultado = resultadosService.crearResultado(estadistica.get());
            respueta = new ResponseEntity<>( resultado, HttpStatus.OK);
        }else{
            respueta = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return respueta;
    }

    @GetMapping("/getResult/{nombreStat}/campos")
    public List<Muestra> getMuestras(@PathVariable String nombreStat){

        List<Muestra> muestras = Lists.newArrayList();

        Optional<Estadistica> estadistica = estadisticaService.buscarPorNombre(nombreStat);
        if(estadistica.isPresent()) {
            Resultado resultado = resultadosService.crearResultado(estadistica.get());
            muestras = resultado.getMuestras();
        }

        return muestras;
    }
    @GetMapping("/getResult/{nombreStat}/{camp}/keys")
    public List<String> getNomVal(@PathVariable String nombreStat, @PathVariable String camp){

        List<String> muestras = Lists.newArrayList();

        Optional<Estadistica> estadistica = estadisticaService.buscarPorNombre(nombreStat);
        if(estadistica.isPresent()) {
            Resultado resultado = resultadosService.crearResultado(estadistica.get());
            Muestra m = Muestra.builder().nombre(camp).build();
            resultado.getMuestras().get(resultado.getMuestras().indexOf(m)).getSucesos().keySet().stream().forEach(muestras::add);
        }

        return muestras;
    }
    @GetMapping("/getResult/{nombreStat}/{camp}/vals")
    public List<Long> getContVal(@PathVariable String nombreStat, @PathVariable String camp){

        List<Long> muestras = Lists.newArrayList();

        Optional<Estadistica> estadistica = estadisticaService.buscarPorNombre(nombreStat);
        if(estadistica.isPresent()) {
            Resultado resultado = resultadosService.crearResultado(estadistica.get());
            Muestra m = Muestra.builder().nombre(camp).build();
            resultado.getMuestras().get(resultado.getMuestras().indexOf(m)).getSucesos().values().stream().forEach(muestras::add);
        }

        return muestras;
    }


}
