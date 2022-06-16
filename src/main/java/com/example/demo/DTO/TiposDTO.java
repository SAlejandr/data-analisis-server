package com.example.demo.DTO;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class TiposDTO implements Serializable {

    @EqualsAndHashCode.Include
    private String nombreTipo;

    private String pregunta;

    private String discriminador;

    private Long limite1;

    private Long limite2;
}
