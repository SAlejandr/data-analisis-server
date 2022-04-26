package com.example.demo.modelo;

import java.time.LocalDateTime;
import java.util.HashMap;

import javax.persistence.Id;

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
	private String id;
	
	private String codFormulario;
	
	private LocalDateTime Fecha;
	
	private HashMap<String, String> respuesta;
	
}
