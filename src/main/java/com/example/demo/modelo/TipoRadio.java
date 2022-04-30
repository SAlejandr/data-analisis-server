package com.example.demo.modelo;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.HashMap;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)

public class TipoRadio extends Tipo implements Serializable {

    private HashMap<String, String> valores;
}
