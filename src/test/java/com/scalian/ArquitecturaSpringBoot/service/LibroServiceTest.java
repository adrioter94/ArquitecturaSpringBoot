package com.scalian.ArquitecturaSpringBoot.service;

import com.scalian.ArquitecturaSpringBoot.model.dto.LibroDTO;
import com.scalian.ArquitecturaSpringBoot.model.entity.Libro;
import com.scalian.ArquitecturaSpringBoot.repository.LibroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LibroServiceTest {

    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroService libroService;

    // ---------- TEST 1: Crear libro exitosamente ----------
    @Test
    void crearLibro_Exitosamente() {
        //Arrange
        LibroDTO dto = new LibroDTO("El Quijote", "Miguel de Cervantes", 863);
        Libro libroGuardado = new Libro(1L, dto.getTitulo(), dto.getAutor(), dto.getPaginas());

        when(libroRepository.save(any(Libro.class))).thenReturn(libroGuardado);

        // Act
        Libro resultado = libroService.crearLibro(dto);

        // Assert
        assertNotNull(resultado);
        assertEquals("El Quijote", resultado.getTitulo());
        verify(libroRepository).save(any(Libro.class));

    }

    // ---------- TEST 2: Error al crear libro inválido (título vacío o páginas < 1) ----------
    @Test
    void crearLibro_Error_TituloVacio_PaginasInvalidas() {
        // Arrange
        LibroDTO sinTitulo = new LibroDTO("", "Autor", 100);
        LibroDTO paginasInvalidas = new LibroDTO("Libro X", "Autor", 0);

        // Act
        Exception excepcionTitulo = assertThrows(IllegalArgumentException.class,
                () -> libroService.crearLibro(sinTitulo));

        Exception excepcionPaginas = assertThrows(IllegalArgumentException.class,
                () -> libroService.crearLibro(paginasInvalidas));

        // Assert
        assertEquals("El título no puede estar vacío", excepcionTitulo.getMessage());
        assertEquals("El número de páginas debe ser mayor que 0", excepcionPaginas.getMessage());
        verify(libroRepository, never()).save(any()); // Verificar que nunca se llame al repositorio
    }

    // ---------- TEST 3: Búsqueda por autor delega correctamente al repositorio ----------
    @Test
    void buscarPorAutor() {
        // Arrange
        String autor = "Stephen King";
        List<Libro> libros = List.of(
                new Libro(1L, "It", autor, 1000),
                new Libro(2L, "Carrie", autor, 350)
        );

        when(libroRepository.buscarPorAutor(autor)).thenReturn(libros);

        // Act
        List<LibroDTO> resultado = libroService.buscarPorAutor(autor);

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("It", resultado.get(0).getTitulo());
        assertEquals("Carrie", resultado.get(1).getTitulo());
        verify(libroRepository, times(1)).buscarPorAutor(autor);
    }

}
