package com.example.demo.modelo;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

public class TipoNumero extends Tipo{

    private Long limiteInferior;

    private Long limiteSuperior;

}
