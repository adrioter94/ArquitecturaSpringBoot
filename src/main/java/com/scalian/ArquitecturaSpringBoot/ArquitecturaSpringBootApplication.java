package com.scalian.ArquitecturaSpringBoot;

import com.scalian.ArquitecturaSpringBoot.model.entity.Libro;
import com.scalian.ArquitecturaSpringBoot.model.entity.Persona;
import com.scalian.ArquitecturaSpringBoot.repository.LibroRepository;
import com.scalian.ArquitecturaSpringBoot.repository.PersonaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ArquitecturaSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArquitecturaSpringBootApplication.class, args);
	}

    /*@Bean
    public CommandLineRunner init(LibroRepository repositorio) {
        return args -> {
            repositorio.save(new Libro(null, "El señor de los anillos", "J. R. R. Tolkien", 1392));
            repositorio.save(new Libro(null, "Harry Potter", "J. K. Rowling", 3872));
        };
    }*/


}
