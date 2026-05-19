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
        return ResponseEntity.ok(recomendacionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecomendacionDTO> obtenerPorId(@PathVariable Long id) {
        RecomendacionDTO r = recomendacionService.findById(id);
        if (r == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(r);
    }

    @PostMapping
    public ResponseEntity<RecomendacionDTO> crear(@RequestBody Recomendacion recomendacion) {
        RecomendacionDTO r = recomendacionService.save(recomendacion);
        return ResponseEntity.ok(r);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecomendacionDTO> actualizar(@PathVariable Long id, @RequestBody Recomendacion recomendacion) {
        RecomendacionDTO actualizada = recomendacionService.update(id, recomendacion);
        if (actualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        recomendacionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // REPORTES
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<RecomendacionDTO>> porUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(recomendacionService.findByUsuario(idUsuario));
    }

    @GetMapping("/filtro/confianza")
    public ResponseEntity<List<RecomendacionDTO>> filtrarPorConfianza(@RequestParam Integer nivelMinimo) {
        return ResponseEntity.ok(recomendacionService.findByNivelConfianza(nivelMinimo));
    }

    @GetMapping("/pelicula/{idPelicula}")
    public ResponseEntity<List<RecomendacionDTO>> porPelicula(@PathVariable Long idPelicula) {
        return ResponseEntity.ok(recomendacionService.findByPelicula(idPelicula));
    }
}