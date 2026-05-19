package cl.duoc.recomendacion_service.controller;

import cl.duoc.recomendacion_service.dto.RecomendacionDTO;
import cl.duoc.recomendacion_service.mapper.RecomendacionMapper;
import cl.duoc.recomendacion_service.model.Recomendacion;
import cl.duoc.recomendacion_service.service.RecomendacionService;
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

    // CRUD
    @GetMapping
    public ResponseEntity<List<RecomendacionDTO>> listar() {
        return ResponseEntity.ok(recomendacionService.findAll().stream()
                .map(mapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecomendacionDTO> obtenerPorId(@PathVariable Long id) {
        Recomendacion r = recomendacionService.findById(id);
        if (r == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mapper.toDTO(r));
    }

    @PostMapping
    public ResponseEntity<RecomendacionDTO> crear(@RequestBody Recomendacion recomendacion) {
        return ResponseEntity.ok(mapper.toDTO(recomendacionService.save(recomendacion)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecomendacionDTO> actualizar(@PathVariable Long id, @RequestBody Recomendacion recomendacion) {
        Recomendacion actualizada = recomendacionService.update(id, recomendacion);
        if (actualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mapper.toDTO(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        recomendacionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // REPORTES
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<RecomendacionDTO>> porUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(recomendacionService.findByUsuario(idUsuario).stream()
                .map(mapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/filtro/confianza")
    public ResponseEntity<List<RecomendacionDTO>> filtrarPorConfianza(@RequestParam Integer nivelMinimo) {
        return ResponseEntity.ok(recomendacionService.findByNivelConfianza(nivelMinimo).stream()
                .map(mapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/pelicula/{idPelicula}")
    public ResponseEntity<List<RecomendacionDTO>> porPelicula(@PathVariable Long idPelicula) {
        return ResponseEntity.ok(recomendacionService.findByPelicula(idPelicula).stream()
                .map(mapper::toDTO).collect(Collectors.toList()));
    }
}