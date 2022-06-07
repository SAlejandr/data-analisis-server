package com.example.demo.modelo;


import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Resultado implements Serializable {

    private String nombre;
    private LocalDate fecha;
    private Long cantidad;

    private Set<Muestra> muestras = Sets.newHashSet();

}
