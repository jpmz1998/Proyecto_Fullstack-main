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

    // --- ENDPOINT ESPECIAL (Para la orquestacion con director-service) ---
    @PostMapping("/promedio-masivo")
    public ResponseEntity<Double> obtenerPromedioPorListaDePeliculas(@RequestBody List<Long> peliculasIds) {
        Double promedio = calificacionService.obtenerPromedioMasivo(peliculasIds);
        return ResponseEntity.ok(promedio);
    }

    // --- ENDPOINTS CRUD ESTÁNDAR ---

    @GetMapping
    public ResponseEntity<List<CalificacionDTO>> listarTodas() {
        List<CalificacionDTO> listaDto = calificacionService.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalificacionDTO> obtenerPorId(@PathVariable Long id) {
        Calificacion calificacion = calificacionService.findById(id);
        if (calificacion == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mapper.toDTO(calificacion));
    }

    @PostMapping
    public ResponseEntity<CalificacionDTO> crear(@RequestBody Calificacion calificacion) {
        Calificacion nuevaCalificacion = calificacionService.save(calificacion);
        return ResponseEntity.ok(mapper.toDTO(nuevaCalificacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CalificacionDTO> actualizar(@PathVariable Long id, @RequestBody Calificacion calificacion) {
        Calificacion calificacionActualizada = calificacionService.update(id, calificacion);
        if (calificacionActualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mapper.toDTO(calificacionActualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        calificacionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
