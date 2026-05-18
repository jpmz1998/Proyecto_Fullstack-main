package cl.duoc.suscripciones_service.controller;

import cl.duoc.suscripciones_service.dto.SuscripcionDTO;
import cl.duoc.suscripciones_service.model.Suscripcion;
import cl.duoc.suscripciones_service.service.SuscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/suscripciones")
public class SuscripcionController {

    @Autowired
    private SuscripcionService suscripcionService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(suscripcionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        SuscripcionDTO dto = suscripcionService.findById(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Suscripcion suscripcion) {
        return new ResponseEntity<>(suscripcionService.save(suscripcion), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Suscripcion suscripcion) {
        SuscripcionDTO actualizada = suscripcionService.update(id, suscripcion);
        if (actualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (!suscripcionService.delete(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
