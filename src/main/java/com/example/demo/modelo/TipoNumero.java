package com.example.demo.modelo;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

public class TipoNumero extends Tipo implements Serializable {

    public TipoNumero() {
        this.setDiscriminador("number");

    }

    private Long limiteInferior;

    private Long limiteSuperior;

}
