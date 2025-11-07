package com.scalian.ArquitecturaSpringBoot.controller;

import com.scalian.ArquitecturaSpringBoot.model.dto.LibroDTO;
import com.scalian.ArquitecturaSpringBoot.model.dto.PersonaDTO;
import com.scalian.ArquitecturaSpringBoot.model.entity.Libro;
import com.scalian.ArquitecturaSpringBoot.model.entity.Persona;
import com.scalian.ArquitecturaSpringBoot.service.LibroService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/libros")
@AllArgsConstructor

public class LibroController {
    private final LibroService libroService;

    @GetMapping
    public List<LibroDTO> obtenerTodos() {
        return libroService.obtenerTodos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Libro crearLibro(@RequestBody LibroDTO libroDTO) {
        return libroService.crearLibro(libroDTO);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgument(IllegalArgumentException ex) {
        return ex.getMessage();
    }

    //JPQL
    @GetMapping("/buscar")
    public List<LibroDTO> buscarPorAutor(@RequestParam String autor) {
        return libroService.buscarPorAutor(autor);
    }

    @GetMapping("/mayores")
    public List<LibroDTO> buscarPorPaginasMayores(@RequestParam int paginas) {
        return libroService.buscarPorPaginasMayores(paginas);
    }
}
