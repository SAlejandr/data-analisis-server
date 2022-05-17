package com.example.demo.modelo;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Set;

@Data
@SuperBuilder
@AllArgsConstructor

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)

public class TipoCheck extends Tipo implements Serializable {

    public TipoCheck() {
        this.setDiscriminador("check");
    }

    private Set<String> valores;
}
