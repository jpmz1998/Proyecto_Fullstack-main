package cl.duoc.suscripciones_service.controller;

import cl.duoc.suscripciones_service.dto.SuscripcionDTO;
import cl.duoc.suscripciones_service.model.Suscripcion;
import cl.duoc.suscripciones_service.service.SuscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/suscripciones")
public class SuscripcionController {

    @Autowired
    private SuscripcionService suscripcionService;

    // CRUD
    @GetMapping
    public ResponseEntity<List<SuscripcionDTO>> listar() {
        return ResponseEntity.ok(suscripcionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuscripcionDTO> buscarPorId(@PathVariable Long id) {
        SuscripcionDTO dto = suscripcionService.findById(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<SuscripcionDTO> registrar(@RequestBody Suscripcion suscripcion) {
        return new ResponseEntity<>(suscripcionService.save(suscripcion), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuscripcionDTO> actualizar(@PathVariable Long id, @RequestBody Suscripcion suscripcion) {
        SuscripcionDTO actualizada = suscripcionService.update(id, suscripcion);
        if (actualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!suscripcionService.delete(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    // REPORTES
    @GetMapping("/filtro/estado")
    public ResponseEntity<List<SuscripcionDTO>> filtrarPorEstado(@RequestParam String estado) {
        return ResponseEntity.ok(suscripcionService.findByEstado(estado));
    }

    @GetMapping("/filtro/plan")
    public ResponseEntity<List<SuscripcionDTO>> filtrarPorPlan(@RequestParam String tipoPlan) {
        return ResponseEntity.ok(suscripcionService.findByPlan(tipoPlan));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<SuscripcionDTO>> porUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(suscripcionService.findByUsuario(idUsuario));
    }

    @GetMapping("/filtro/monto")
    public ResponseEntity<List<SuscripcionDTO>> filtrarPorMonto(
            @RequestParam Integer montoMin,
            @RequestParam Integer montoMax) {
        return ResponseEntity.ok(suscripcionService.findByRangoDeMonto(montoMin, montoMax));
    }
}