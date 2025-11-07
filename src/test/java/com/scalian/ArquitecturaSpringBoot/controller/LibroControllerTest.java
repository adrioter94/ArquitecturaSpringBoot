package com.scalian.ArquitecturaSpringBoot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scalian.ArquitecturaSpringBoot.model.dto.LibroDTO;
import com.scalian.ArquitecturaSpringBoot.model.entity.Libro;
import com.scalian.ArquitecturaSpringBoot.service.LibroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LibroController.class)
@AutoConfigureMockMvc(addFilters = false)
class LibroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LibroService libroService;

    @Test
    void crearLibroValido_200() throws Exception {
        // Arrange
        LibroDTO dto = new LibroDTO("El Quijote", "Miguel de Cervantes", 863);
        Libro libroGuardado = new Libro(1L, dto.getTitulo(), dto.getAutor(), dto.getPaginas());
        when(libroService.crearLibro(any(LibroDTO.class))).thenReturn(libroGuardado);

        // Act
        var resultActions = mockMvc.perform(post("/api/libros")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)));

        // Assert
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("El Quijote"))
                .andExpect(jsonPath("$.autor").value("Miguel de Cervantes"));
    }

    @Test
    void crearLibroInvalido_400() throws Exception {
        // Arrange
        LibroDTO dtoInvalido = new LibroDTO("", "Autor X", 0);
        when(libroService.crearLibro(any(LibroDTO.class)))
                .thenThrow(new IllegalArgumentException("Datos inválidos del libro"));

        // Act
        var resultActions = mockMvc.perform(post("/api/libros")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dtoInvalido)));

        // Assert
        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    void buscarPorAutor() throws Exception {
        // Arrange
        String autor = "Stephen King";
        List<LibroDTO> libros = List.of(
                new LibroDTO("It", autor, 450),
                new LibroDTO("Carrie", autor, 300)
        );
        when(libroService.buscarPorAutor(eq(autor))).thenReturn(libros);

        // Act
        var resultActions = mockMvc.perform(get("/api/libros/buscar")
                .param("autor", autor)
                .contentType(MediaType.APPLICATION_JSON));

        // Assert
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].titulo").value("It"))
                .andExpect(jsonPath("$[1].titulo").value("Carrie"));
    }
}
