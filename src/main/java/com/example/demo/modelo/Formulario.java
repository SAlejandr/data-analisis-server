package com.example.demo.modelo;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import javax.persistence.Id;
import java.util.HashMap;

@Data

@Document
public class Formulario {

	@Id
	private String id;

	private String nombre;

	private HashMap<String, Tipo> campos;
	
}
