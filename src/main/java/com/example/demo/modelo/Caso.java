package com.example.demo.modelo;

import java.time.LocalDateTime;
import java.util.HashMap;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class Caso {

	@Id
	@EqualsAndHashCode.Include
	private String id;

	@DBRef
	private Formulario formulario;
	
	private LocalDateTime fecha;
	
	private HashMap<String, String> respuesta;
	
}
