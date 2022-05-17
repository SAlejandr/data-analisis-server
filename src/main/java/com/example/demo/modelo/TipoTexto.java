package com.example.demo.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)


public class TipoTexto extends Tipo implements Serializable {

    public TipoTexto() {
        this.setDiscriminador("texto");
    }

    private Long limiteDeLetras;
}
