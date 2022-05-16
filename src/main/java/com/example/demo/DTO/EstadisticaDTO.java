package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class EstadisticaDTO {

    private String nombre;

    private String titulo;

    private boolean privada;

    private String descripcion;

    private LocalDate fechaLimite;
}
