package cl.duoc.calificacion_service.controller;

import cl.duoc.calificacion_service.dto.CalificacionDTO;
import cl.duoc.calificacion_service.mapper.CalificacionMapper;
import cl.duoc.calificacion_service.model.Calificacion;
import cl.duoc.calificacion_service.service.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/calificaciones")
public class CalificacionController {

    @Autowired
    private CalificacionService calificacionService;

    @Autowired
    private CalificacionMapper mapper;

    // Endpoint especial para Feign
    @PostMapping("/promedio-masivo")
    public ResponseEntity<Double> obtenerPromedioPorListaDePeliculas(@RequestBody List<Long> peliculasIds) {
        return ResponseEntity.ok(calificacionService.obtenerPromedioMasivo(peliculasIds));
    }

    // CRUD
    @GetMapping
    public ResponseEntity<List<CalificacionDTO>> listarTodas() {
        List<CalificacionDTO> nuevaLisdta = calificacionService.findAll();
        return ResponseEntity.ok(nuevaLisdta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalificacionDTO> obtenerPorId(@PathVariable Long id) {
        CalificacionDTO c = calificacionService.findById(id);
        if (c == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(c);
    }

    @PostMapping
    public ResponseEntity<CalificacionDTO> crear(@RequestBody Calificacion calificacion) {
        return ResponseEntity.ok(calificacionService.save(calificacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CalificacionDTO> actualizar(@PathVariable Long id, @RequestBody Calificacion calificacion) {
        CalificacionDTO actualizada = calificacionService.update(id, calificacion);
        if (actualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        calificacionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // REPORTES
    @GetMapping("/pelicula/{idPelicula}")
    public ResponseEntity<List<CalificacionDTO>> porPelicula(@PathVariable Long idPelicula) {
        return ResponseEntity.ok(calificacionService.findByPelicula(idPelicula));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<CalificacionDTO>> porUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(calificacionService.findByUsuario(idUsuario));
    }

    @GetMapping("/pelicula/{idPelicula}/promedio")
    public ResponseEntity<Double> promedioPorPelicula(@PathVariable Long idPelicula) {
        return ResponseEntity.ok(calificacionService.promedioPorPelicula(idPelicula));
    }

    @GetMapping("/filtro/puntaje-minimo")
    public ResponseEntity<List<CalificacionDTO>> filtrarPorPuntajeMinimo(@RequestParam Integer puntaje) {
        return ResponseEntity.ok(calificacionService.findByPuntajeMinimo(puntaje));
    }
}