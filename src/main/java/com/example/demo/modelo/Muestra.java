package com.example.demo.modelo;

import lombok.*;

import java.io.Serializable;
import java.util.TreeMap;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Muestra implements Serializable {

    @EqualsAndHashCode.Include
    private String nombre;

    private String tipo;

    private TreeMap<String, Long> sucesos;
}
