package com.scalian.ArquitecturaSpringBoot.integration;

import com.scalian.ArquitecturaSpringBoot.model.dto.LibroDTO;
import com.scalian.ArquitecturaSpringBoot.model.entity.Libro;
import com.scalian.ArquitecturaSpringBoot.repository.LibroRepository;
import com.scalian.ArquitecturaSpringBoot.service.LibroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
class LibroIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16")
            .withDatabaseName("curso_testing")
            .withUsername("scalian")
            .withPassword("scalian");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private LibroService libroService;

    @Test
    void crearYRecuperarLibro() {
        // Arrange
        LibroDTO dto = new LibroDTO("El Quijote", "Miguel de Cervantes", 863);

        // Act
        Libro creado = libroService.crearLibro(dto);
        Libro recuperado = libroRepository.findById(creado.getId()).orElseThrow();

        // Assert
        assertThat(recuperado.getId()).isNotNull();
        assertThat(recuperado.getTitulo()).isEqualTo("El Quijote");
        assertThat(recuperado.getAutor()).isEqualTo("Miguel de Cervantes");
        assertThat(recuperado.getPaginas()).isEqualTo(863);
    }
}
