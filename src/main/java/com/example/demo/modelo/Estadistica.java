package com.example.demo.modelo;


import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDate;
import java.util.HashSet;
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

    private Set<Caso> casos = new HashSet<>();

    private LocalDate fechaLimite;

}
