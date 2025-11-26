package com.scalian.ArquitecturaSpringBoot.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Libro {

    @Id

    // He tenido que poner esto para qu no me falle, aunque no utilice Libro en Oracle
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "libro_seq")
    @SequenceGenerator(name = "libro_seq", sequenceName = "LIBRO_SEQ", allocationSize = 1)
    private Long id;

    private String titulo;
    private String autor;
    private int paginas;

}
