package com.example.demo.modelo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Document
public class Estadistica {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Indexed
    private String nombre;

    private String titulo;

    private boolean privada;

    @DBRef
    private Formulario formulario;

    private String descripcion;

    private Set<Caso> casos;

    private Integer limiteNumerico;

    private LocalDateTime fechaLimite;

}
