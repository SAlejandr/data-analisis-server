package com.example.demo.controlador;

import com.example.demo.DTO.TiposDTO;
import com.example.demo.modelo.Formulario;
import com.example.demo.modelo.Tipo;
import com.example.demo.modelo.TipoNumero;
import com.example.demo.modelo.TipoTexto;
import com.example.demo.servicio.IFormularioService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    public ResponseEntity<Formulario> annadirFormulario(@RequestParam String nombre, @RequestParam Boolean privado){

        ResponseEntity<Formulario> respuesta;

        if(!servicio.buscarPorNombre(nombre).isPresent()){
            Formulario formulario = Formulario.builder().
                    nombre(nombre).
                    privado(privado).
                    build();
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

    @PutMapping("/upd/{form}/campos")
    public ResponseEntity<Formulario> modificarFormulario(@PathVariable String form,@RequestBody HashMap<String, Tipo> campos){

        ResponseEntity<Formulario> respuesta;

        if(servicio.buscarPorNombre(form).isPresent()){
            Formulario formulario = servicio.buscarPorNombre(form).get();
            System.err.println(campos.toString());

            if(formulario.getCampos() == null || formulario.getCampos().isEmpty())
                formulario.setCampos(campos);



            System.err.println(formulario.toString());
            servicio.guardarFormulario(formulario);
            respuesta = new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else{
            respuesta = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return respuesta;

    }
    @PutMapping("/upd1/{form}/campos")
    public ResponseEntity<Formulario> darCamposFormulario(@PathVariable String form,@RequestBody List<TiposDTO> campos){

        ResponseEntity<Formulario> respuesta;

        if(servicio.buscarPorNombre(form).isPresent()){
            Formulario formulario = servicio.buscarPorNombre(form).get();
            System.err.println(campos.toString());

            HashMap<String, Tipo> mapCampos = Maps.newHashMap();

            campos.stream().forEach(campo ->{

                if(campo.getDiscriminador().equalsIgnoreCase("texto")){

                    if(campo.getLimite1() == null)
                        campo.setLimite1(255L);

                    TipoTexto tipo = TipoTexto.builder()
                            .nombreTipo(campo.getNombreTipo())
                            .pregunta(campo.getPregunta())
                            .discriminador(campo.getDiscriminador())
                            .limiteDeLetras(campo.getLimite1())
                            .build();

                    mapCampos.put(tipo.getNombreTipo(), tipo);

                } else if (campo.getDiscriminador().equalsIgnoreCase("number")) {

                    if(campo.getLimite1() == null)
                        campo.setLimite1(1L);

                    if(campo.getLimite2() == null ||campo.getLimite2() < campo.getLimite1())
                        campo.setLimite2(campo.getLimite1()*2);

                    TipoNumero tipo = TipoNumero.builder()
                            .nombreTipo(campo.getNombreTipo())
                            .pregunta(campo.getPregunta())
                            .discriminador(campo.getDiscriminador())
                            .limiteInferior(campo.getLimite1())
                            .limiteSuperior(campo.getLimite2())
                            .build();

                    mapCampos.put(tipo.getNombreTipo(), tipo);
                }


            });


            if(formulario.getCampos() == null || formulario.getCampos().isEmpty())
                formulario.setCampos(mapCampos);



            System.err.println(formulario.toString());
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
