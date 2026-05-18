package cl.duoc.pelicula_service.controller;

import cl.duoc.pelicula_service.dto.PeliculaDTO;
import cl.duoc.pelicula_service.mapper.PeliculaMapper;
import cl.duoc.pelicula_service.model.Pelicula;
import cl.duoc.pelicula_service.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private PeliculaMapper mapper;

    // Endpoint para el Feign Client del Director (Retorna solo IDs)
    @GetMapping("/director/{idDirector}/ids")
    public ResponseEntity<List<Long>> obtenerIdsPeliculasPorDirector(@PathVariable Long idDirector) {
        return ResponseEntity.ok(peliculaService.obtenerIdsPorDirector(idDirector));
    }

    // --- ¡NUEVO ENDPOINT PARA LA PRODUCTORA! ---
    @GetMapping("/productora/{idProductora}/ids")
    public ResponseEntity<List<Long>> obtenerIdsPeliculasPorProductora(@PathVariable Long idProductora) {
        return ResponseEntity.ok(peliculaService.obtenerIdsPorProductora(idProductora));
    }

    // --- CRUD ESTÁNDAR RETORNANDO DTOs ---

    @GetMapping
    public ResponseEntity<List<PeliculaDTO>> listarTodas() {
        List<PeliculaDTO> peliculasDto = peliculaService.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(peliculasDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeliculaDTO> obtenerPorId(@PathVariable Long id) {
        Pelicula pelicula = peliculaService.findById(id);
        if (pelicula == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mapper.toDTO(pelicula));
    }

    @PostMapping
    public ResponseEntity<PeliculaDTO> crear(@RequestBody Pelicula pelicula) {
        Pelicula nuevaPelicula = peliculaService.save(pelicula);
        return ResponseEntity.ok(mapper.toDTO(nuevaPelicula));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeliculaDTO> actualizar(@PathVariable Long id, @RequestBody Pelicula pelicula) {
        Pelicula peliculaActualizada = peliculaService.update(id, pelicula);
        if (peliculaActualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mapper.toDTO(peliculaActualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        peliculaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // --- ¡NUEVO ENDPOINT: FILTRO DE EDAD! ---
    // Se usará asi: GET /api/v1/peliculas/filtro/edad?esPara18=true
    @GetMapping("/filtro/edad")
    public ResponseEntity<List<PeliculaDTO>> filtrarPeliculasPorEdad(@RequestParam Boolean esPara18) {
        List<PeliculaDTO> peliculasFiltradas = peliculaService.filtrarPorClasificacionEdad(esPara18)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(peliculasFiltradas);
    }
    //en POSTMAN
    //trae las de mayoria de edad
    // http://localhost:8093/api/v1/peliculas/filtro/edad?esPara18=true

    //trae para todo publico
    // http://localhost:8093/api/v1/peliculas/filtro/edad?esPara18=false
}