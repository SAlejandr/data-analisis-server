package com.example.demo.modelo;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

public class TipoNumero extends Tipo implements Serializable {

    private Long limiteInferior;

    private Long limiteSuperior;

}
