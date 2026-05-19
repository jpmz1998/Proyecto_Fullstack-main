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

    // Endpoints para Feign Clients
    @GetMapping("/director/{idDirector}/ids")
    public ResponseEntity<List<Long>> obtenerIdsPorDirector(@PathVariable Long idDirector) {
        return ResponseEntity.ok(peliculaService.obtenerIdsPorDirector(idDirector));
    }

    @GetMapping("/productora/{idProductora}/ids")
    public ResponseEntity<List<Long>> obtenerIdsPorProductora(@PathVariable Long idProductora) {
        return ResponseEntity.ok(peliculaService.obtenerIdsPorProductora(idProductora));
    }

    // CRUD
    @GetMapping
    public ResponseEntity<List<PeliculaDTO>> listarTodas() {
        return ResponseEntity.ok(peliculaService.findAll().stream()
                .map(mapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeliculaDTO> obtenerPorId(@PathVariable Long id) {
        Pelicula pelicula = peliculaService.findById(id);
        if (pelicula == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mapper.toDTO(pelicula));
    }

    @PostMapping
    public ResponseEntity<PeliculaDTO> crear(@RequestBody Pelicula pelicula) {
        return ResponseEntity.ok(mapper.toDTO(peliculaService.save(pelicula)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeliculaDTO> actualizar(@PathVariable Long id, @RequestBody Pelicula pelicula) {
        Pelicula actualizada = peliculaService.update(id, pelicula);
        if (actualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mapper.toDTO(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        peliculaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // REPORTES
    @GetMapping("/filtro/edad")
    public ResponseEntity<List<PeliculaDTO>> filtrarPorEdad(@RequestParam Boolean esPara18) {
        return ResponseEntity.ok(peliculaService.filtrarPorClasificacionEdad(esPara18)
                .stream().map(mapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/filtro/genero")
    public ResponseEntity<List<PeliculaDTO>> filtrarPorGenero(@RequestParam String genero) {
        return ResponseEntity.ok(peliculaService.findByGenero(genero)
                .stream().map(mapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/filtro/duracion")
    public ResponseEntity<List<PeliculaDTO>> filtrarPorDuracion(
            @RequestParam Integer min,
            @RequestParam Integer max) {
        return ResponseEntity.ok(peliculaService.findByDuracion(min, max)
                .stream().map(mapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/buscar/nombre")
    public ResponseEntity<List<PeliculaDTO>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(peliculaService.findByNombre(nombre)
                .stream().map(mapper::toDTO).collect(Collectors.toList()));
    }
}