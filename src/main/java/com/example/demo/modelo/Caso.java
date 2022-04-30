package com.example.demo.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;

import javax.persistence.Id;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Caso implements Serializable {

	@Id
	@EqualsAndHashCode.Include
	private String id;

	@DBRef
	private Formulario formulario;
	
	private LocalDateTime fecha;
	
	private HashMap<String, String> respuesta;
	
}
