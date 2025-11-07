package com.scalian.ArquitecturaSpringBoot.model.dto;

import lombok.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroDTO {
    //No queremos que muestre el campo ID.

    @NotBlank(message = "El título no puede estar vacío")
    @Size(min = 2)
    private String titulo;

    @NotBlank(message = "El autor no puede estar vacío")
    @Size(min = 2)
    private String autor;

    @Min(value = 1, message = "El número de páginas debe ser mayor que 0")
    private int paginas;

}
