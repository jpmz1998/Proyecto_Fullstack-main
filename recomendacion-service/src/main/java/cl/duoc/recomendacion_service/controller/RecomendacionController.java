package cl.duoc.recomendacion_service.controller;

import cl.duoc.recomendacion_service.dto.RecomendacionDTO;
import cl.duoc.recomendacion_service.mapper.RecomendacionMapper;
import cl.duoc.recomendacion_service.model.Recomendacion;
import cl.duoc.recomendacion_service.service.RecomendacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/recomendaciones")
public class RecomendacionController {

    @Autowired
    private RecomendacionService recomendacionService;

    @Autowired
    private RecomendacionMapper mapper;

    // 1. Listar todas
    @GetMapping
    public ResponseEntity<List<RecomendacionDTO>> listarTodas() {
        List<RecomendacionDTO> lista = recomendacionService.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    // 2. Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<RecomendacionDTO> obtenerPorId(@PathVariable Long id) {
        Recomendacion recomendacion = recomendacionService.findById(id);
        if (recomendacion == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mapper.toDTO(recomendacion));
    }

    // 3. ENDPOINT CUSTOM: Obtener las recomendaciones de un usuario específico
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<RecomendacionDTO>> obtenerRecomendacionesPorUsuario(@PathVariable Long idUsuario) {
        List<RecomendacionDTO> lista = recomendacionService.obtenerPorUsuario(idUsuario)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    // 4. Crear nueva recomendación
    @PostMapping
    public ResponseEntity<RecomendacionDTO> crear(@Valid @RequestBody Recomendacion recomendacion) {
        Recomendacion nueva = recomendacionService.save(recomendacion);
        return ResponseEntity.ok(mapper.toDTO(nueva));
    }

    // 5. Actualizar recomendación
    @PutMapping("/{id}")
    public ResponseEntity<RecomendacionDTO> actualizar(@PathVariable Long id, @Valid @RequestBody Recomendacion recomendacion) {
        Recomendacion actualizada = recomendacionService.update(id, recomendacion);
        if (actualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mapper.toDTO(actualizada));
    }

    // 6. Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        recomendacionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
