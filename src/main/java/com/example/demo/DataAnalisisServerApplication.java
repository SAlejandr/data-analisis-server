package com.example.demo;

import com.example.demo.modelo.Estadistica;
import com.example.demo.modelo.Formulario;
import com.example.demo.modelo.Tipo;
import com.example.demo.modelo.TipoTexto;
import com.example.demo.repositorio.EstadisticaRepository;
import com.example.demo.repositorio.FormularioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class DataAnalisisServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataAnalisisServerApplication.class, args);
	}

}
