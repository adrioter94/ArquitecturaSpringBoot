package com.scalian.ArquitecturaSpringBoot.service;

import com.scalian.ArquitecturaSpringBoot.model.dto.LibroDTO;
import com.scalian.ArquitecturaSpringBoot.model.dto.PersonaDTO;
import com.scalian.ArquitecturaSpringBoot.model.entity.Libro;
import com.scalian.ArquitecturaSpringBoot.model.entity.Persona;
import com.scalian.ArquitecturaSpringBoot.repository.LibroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LibroService {

    private final LibroRepository libroRepository;

    public List<LibroDTO> obtenerTodos() {
        return libroRepository.findAll().stream()
                .map(l -> {
                    LibroDTO libroDTO = new LibroDTO();
                    libroDTO.setTitulo(l.getTitulo());
                    libroDTO.setAutor(l.getAutor());
                    libroDTO.setPaginas(l.getPaginas());
                    return libroDTO;
                })
                .collect(Collectors.toList());

    }

    public Libro crearLibro(LibroDTO libroDTO) {

        if (libroDTO.getTitulo() == null || libroDTO.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }
        if (libroDTO.getPaginas() < 1) {
            throw new IllegalArgumentException("El número de páginas debe ser mayor que 0");
        }

        Libro libro = new Libro(null, libroDTO.getTitulo(), libroDTO.getAutor(), libroDTO.getPaginas());
        return libroRepository.save(libro);
    }

    //JPQL
    public List<LibroDTO> buscarPorAutor(String autor) {
        return libroRepository.buscarPorAutor(autor)
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    //Query
    public List<LibroDTO> buscarPorPaginasMayores(int paginas) {
        return libroRepository.findByPaginasGreaterThan(paginas).stream()
                .map(this::convertirADTO)
                .toList();
    }

    private LibroDTO convertirADTO(Libro libro) {
        return new LibroDTO(libro.getTitulo(), libro.getAutor(), libro.getPaginas());
    }
}
