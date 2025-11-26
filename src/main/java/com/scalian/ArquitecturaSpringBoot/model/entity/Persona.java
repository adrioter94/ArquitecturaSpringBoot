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
public class Persona {

    @Id

    // He tenido que poner esto para qu no me falle, aunque no utilice Persona en Oracle
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persona_seq")
    @SequenceGenerator(name = "persona_seq", sequenceName = "PERSONA_SEQ", allocationSize = 1)
    private Long id;

    private String nombre;
    private int edad;
}
