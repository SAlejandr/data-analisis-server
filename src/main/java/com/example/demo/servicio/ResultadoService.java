package com.example.demo.servicio;

import com.example.demo.modelo.Estadistica;
import com.example.demo.modelo.Formulario;
import com.example.demo.modelo.Muestra;
import com.example.demo.modelo.Resultado;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public class ResultadoService implements IResultadosService {

    @Override
    public Resultado crearResultado(Estadistica estadistica) {

        Resultado resultado = Resultado.builder()
                .nombre(estadistica.getNombre())
                .fecha(LocalDate.now())
                .cantidad(estadistica.getCasos().isEmpty()? 0 : Long.valueOf(estadistica.getCasos().size()))
                .build();

        Set<Muestra> muestras = Sets.newHashSet();
        //Empiezo a recorrer cada caso
        estadistica.getCasos().stream().forEach(caso -> {
            Formulario form = caso.getFormulario();
            //Doy el resultado de cada campo
            form.getCampos().keySet().stream().forEach( key -> {
                Muestra muestra = Muestra.builder().nombre(key).tipo(form.getCampos().get(key).getDiscriminador()).build();

                //compruebo que exista la muestra del capo, y si no, la creo
                if(muestras.contains(muestra)){
                    Muestra finalMuestra = muestra;
                    muestra = muestras.stream().filter(m ->m.equals(finalMuestra)).findAny().get();
                    //En caso de existir el suceso de la respuesta, obtener su conteo y aumentarlo
                    if(muestra.getSucesos().containsKey(caso.getRespuesta().get(key))){
                        String clave= form.getCampos().get(key).getNombreTipo();
                        muestra.getSucesos().put(caso.getRespuesta().get(clave) ,muestra.getSucesos().get(clave)+1);
                    }else{//Suceso no exsite, se crea
                        String clave= form.getCampos().get(key).getNombreTipo();
                        muestra.getSucesos().put(caso.getRespuesta().get(clave) , Long.valueOf(1));
                    }

                }else{ //Se crea la muestra
                    muestras.add(muestra);
                    String clave= form.getCampos().get(key).getNombreTipo();
                    muestra.getSucesos().put(caso.getRespuesta().get(clave) , Long.valueOf(1));
                }
                //Se añade la muestra
                muestras.add(muestra);
            });

        });

        //Una vez se han recorrido todos los casos, se añaden las muestras al resultado

        resultado.setMuestras(muestras);

        return resultado;
    }
}
