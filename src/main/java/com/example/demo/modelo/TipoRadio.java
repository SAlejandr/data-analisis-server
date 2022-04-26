package com.example.demo.modelo;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)

public class TipoRadio extends Tipo{

    private HashMap<String, String> valores;
}
