package com.example.demo.modelo;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.HashMap;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Document
public class Formulario implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Indexed
    private String nombre;

    private Boolean privado;

    private HashMap<String, Tipo> campos;

}
